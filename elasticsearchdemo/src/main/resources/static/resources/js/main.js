var app = angular.module("movie", ['ngSanitize']);

app.controller("MovieController", function ($scope, $http,$location,$window) {

    $scope.movies = [];
    $scope.type = 'AUTO';


    // $scope.add = function(movie){
    //     $http.post('/api/movies/', movie);
    // };
    $scope.search_by_actor = function () {
        if ($scope.search_by_actor_Input.length>2){
            $http.get('/api/movies/searchbyactor', {params: {q: $scope.search_by_actor_Input, page: 1, size: 10}})
                .then(function (resp) {
                    $scope.movies = resp.data;
                })
        } 
    };
    $scope.search = function () {
        if (!$scope.searchInput.length) {
            $scope.findAll();
            return;
        }
        if ($scope.type == 'AUTO') {
            $scope.autocomplete();
            return;
        }
        $http.get('/api/movies/search', {params: {q: $scope.searchInput, type: $scope.type, page: 1, size: 10}})
            .then(function (resp) {
                $scope.movies = resp.data;
            })
    };

    // $scope.load = function(){
    //     $http.get('/resources/movies.json')
    //         .then(function(resp){
    //             resp.data.forEach((movie) => {
    //                 $scope.add(movie);
    //             });
    //         })
    // }


    $scope.findAll = function () {
        $http.get('/api/movies/')
            .then(function (resp) {
                $scope.movies = resp.data._embedded.movies;
            })
    };
    $scope.go = function (movie) {
        $window.location = movie.img_url;
        $location.path(movie.img_url);
    };
    $scope.autocomplete = function () {

        if (!$scope.searchInput.length) {
            return;
        }

        $http.get('/api/movies/autocomplete', {params: {q: $scope.searchInput}})
            .then(function (resp) {
                let data = JSON.parse(resp.data);
                $scope.movies = data.hits.hits.map(h => {
                        let obj = {};
                if (h.highlight && h.highlight.hasOwnProperty("name")) {
                    obj.name = h.highlight.name;
                } else {
                    obj.name = h._source.name;
                }

                if (h.highlight && h.highlight.hasOwnProperty("description")) {
                    obj.description = h.highlight.description;
                } else {
                    obj.description = h._source.description;
                }
                if (h.highlight && h.highlight.hasOwnProperty("id")) {
                    obj.img_url = "/movie/"+h.highlight.id;
                } else {
                    obj.img_url = "/movie/"+h._source.id;
                }
                if (h.highlight && h.highlight.hasOwnProperty("image")) {
                    // obj.image = h.highlight.image;
                    obj.image = h.highlight.image;
                } else {
                    obj.image = h._source.image;
                }
                return obj;
            })
            })
    };

    $scope.findAll();

});




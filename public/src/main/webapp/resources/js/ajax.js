function get() {
    $.ajax({
        url: "/ajax/getMovies",
        type: "GET",
        data: {
            text: ($('#finder')).val()
        },
        success: function (movies) {
            $("#text").text('');
            for (var i = 0; i < movies.length; i++) {
                $("#text").prepend('<br>' + movies[i].name).fadeIn('slow');
            }
        }
    })
}
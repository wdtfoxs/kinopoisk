/**
 * Created by Vlad.M on 10.12.2016.
 */

function rate5(id) {
    rate(5,id);
}
function rate4(id) {
    rate(4,id);
}
function rate3(id) {
    rate(3,id);
}
function rate2(id) {
    rate(2,id);
}
function rate1(id) {
    rate(1,id);
}

function rate(star,id) {
    $.ajax({
        url: "/movie/" + id + "/rate",
        type: "POST",
        data: {
            value: star
        },
        success: function (rating) {
            change_rating(rating);
        }
    });
    setviewrating(star);
}

function setviewrating(stars) {
    $("#rating").html("");
    for (var i = 0; i < stars; i++){
        $("#rating").append("<span class='glyphicon glyphicon-star' style='color: greenyellow'></span>");
    }
}
function change_rating(rating) {
    $(document).ready(function () {
            $("#mv_rating").html(rating);
            return true;
        }
    )
}



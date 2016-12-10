/**
 * Created by Vlad.M on 10.12.2016.
 */
function rate5(id) {
    $.ajax({
        url: "/movie/"+id+"/rate",
        type: "POST",
        data: {
            value: 5
        },
        success: function (rating) {
            $(document).ready(function(){
                if (rating > 1)
                $("#star1").
            });
            return true;
        }
    })
}

function setviewrating(rating) {
    
}

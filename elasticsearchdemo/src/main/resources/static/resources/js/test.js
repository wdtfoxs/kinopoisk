function addComment(id) {
    $.ajax({
        url: "/movie/"+id+"/comment",
        type: "POST",
        data: {
            content: document.getElementById("comment_text").value
        },
        success: function (comment) {
            // window.location.href = "/cart/get";
            // alert('Удалено');
            var date = new Date();
            $(document).ready(function(){
                $("#comlist").prepend('<ul class="single_list">' +
                    '<div class="panel panel-primary">' +
                    '<div class="panel-body">' +
                    comment.user.username +":"+ date.getDate()+"."+ date.getMonth()+"." + date.getFullYear() +" " + date.getHours()+":"+ date.getMinutes()
                    +":"+ date.getSeconds() +
                    '</div> ' +
                    '<div class="panel-footer">'+comment.content+'</div> ' +
                    '</div> ' +
                    '</ul>').hide().fadeIn('slow');
                });
            return true;
        }
    })
}
function showsearch() {
    $("#sh_search").show();
}
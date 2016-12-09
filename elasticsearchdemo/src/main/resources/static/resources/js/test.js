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
            $(document).ready(function(){
                $("#comlist").prepend('<ul class="single_list">' +
                    '<div class="panel panel-primary">' +
                    '<div class="panel-body">' +
                    comment.user.name +":"+ comment.date+
                    '</div> ' +
                    '<div class="panel-footer">'+comment.content+'</div> ' +
                    '</div> ' +
                    '</ul>').hide().fadeIn('slow');
                });
            return true;
        }
    })
}
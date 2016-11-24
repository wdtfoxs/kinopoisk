function add() {
    var name = document.getElementById("name").innerHTML;
    var descr = document.getElementById("descr").innerHTML;
    $.ajax({
        url: "/movie",
        type: "POST",
        data: {
            name: name,
            description: descr
        },
        success: function () {
            // alert('Удалено');
            return true;
        }
    })
}
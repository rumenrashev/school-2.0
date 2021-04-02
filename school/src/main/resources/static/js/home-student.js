$(document).ready(function() {
    $("#marks").hide();
    $("#subjects").hide();
});
$(document).ready(function () {
    $('#mark-button').click(() => {
        $('#marks').show();
        $('#subjects').hide();
        $('#mark-button').hide();
        $('#subject-button').show();
    })

    $('#subject-button').click(() => {
        $('#subjects').show();
        $('#marks').hide();
        $('#subject-button').hide();
        $('#mark-button').show();
    })

});

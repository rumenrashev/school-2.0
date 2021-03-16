$( document ).ready(function() {
    $('#add-student-container').hide();
    $('#hide-add-student-button').hide()
    $('#students-button').click(()=>{
        $('#students-button').hide();
        $('#add-student-button').show();
    })

    $('#add-student-button').click(()=>{
        $('#add-student-button').hide();
        $('#hide-add-student-button').show();
        $('#add-student-container').show();
    })

    $('#hide-add-student-button').click(()=>{
        $('#hide-add-student-button').hide();
        $('#add-student-button').show()
        $('#add-student-container').hide();
    })
 });

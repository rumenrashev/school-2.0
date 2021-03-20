function students(groupId) {
    $(document).ready(function () {
        $('#add-student-btn').show();
        $('#add-student-container').hide();
        $('#students-btn').hide();
        $('#students-container').empty();
        let table =
            '<table class="table table-striped" style="width:100%">' +
                '<thead>' +
                    '<tr>' +
                        '<td colspan="2" style="text-align: center"><h3>Ученици</h3></td>' +
                    '</tr>' +
                    '<tr>' +
                       '<td>#</td>' +
                       '<td>Име</td>' +
                    '</tr>' +
                '</thead>' +
                '<tbody id="students">' +
                '</tbody>' +
            '</table>'
        + '<div style="height: 70px"></div>';

        $('#students-container').append(table);

        fetch('http://localhost:8000/students/all/' + groupId ).
        then((response) => response.json()).
        then((json) => json.forEach((student, idx) => {
            let number = '<td>' + (idx + 1) + '</td>/';
            let name = '<td>' + student.firstName +  ' ' + student.middleName + ' ' + student.lastName  + '</td>';
            let row = '<tr>' + number + name +'</tr>'
            $('#students').append(row)
        }));
        $('#students-container').show();
    });
}


    $(document).ready(function() {
        $('#add-student-btn').hide();
    });


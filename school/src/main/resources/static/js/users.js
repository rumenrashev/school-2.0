

$( document ).ready(function() {
    const mainUrl = 'http://localhost:8000/admin';
    function createRow (user) {
        console.log(user.username)
        let usernameColumn = '<td>' + user.username + '</td>';
        let editButtonColumn =
            '<td>' +
            '<a href="/admin/edit-user/' + user.id  + '" class="btn btn-primary">Edit</a>' +
            '</td>';
        let row = '<tr>' + usernameColumn + editButtonColumn + '</tr>';
        $('.user-container').append(row);
    }

    function loadUsersByGroup(url,groupName){
        $('.user-container').empty();
        $('#user-group').empty();
        fetch(url).
        then((response) => response.json()).
        then((json) => json.forEach((user, idx) => {
            createRow(user)
        }))
        $('#user-group').append(groupName)
    }


    $('#admin-button').click(() => {
        loadUsersByGroup(mainUrl + '/admins','Administrators:')
    })
    $('#teacher-button').click(() => {
        loadUsersByGroup(mainUrl + '/teachers','Teachers:')
    })
    $('#user-button').click(() => {
        loadUsersByGroup(mainUrl + '/users','Users:')
    })
});

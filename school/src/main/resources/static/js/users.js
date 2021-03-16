const mainUrl = 'http://localhost:8000/admin/users';
function createRow (user) {
    let usernameColumn = '<td>' + user.username + '</td>';
    let editButtonColumn =
        '<td>' +
        '<a href="/admin/users/edit-user/' + user.id  + '" class="btn btn-primary">Edit</a>' +
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

$( document ).ready(function() {

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

$(document).load( loadUsersByGroup(mainUrl + '/users','Users:'));


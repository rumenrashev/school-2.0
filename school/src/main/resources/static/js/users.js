const mainUrl = '/api';
function createRow (user) {
    let usernameColumn = '<td>' + user.username + '</td>';
    let editButtonColumn =
        '<td>' +
        '<a href="/users/edit-user/' + user.id  + '" class="btn btn-primary">Промени</a>' +
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
        loadUsersByGroup(mainUrl + '/admins','Администратори:')
    })
    $('#teacher-button').click(() => {
        loadUsersByGroup(mainUrl + '/teachers','Учители:')
    })
    $('#user-button').click(() => {
        loadUsersByGroup(mainUrl + '/users','Потребители:')
    })
});

$(document).load( loadUsersByGroup(mainUrl + '/users','Потребители:'));


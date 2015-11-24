function getContacts() {
    $.getJSON('contacts?action=list', function(data) {
        $.each(data, function(key, val) {
            var tr = $('<tr></tr>');
            $.each(val, function(k, v) {
                $('<td>' + v + '</td>').appendTo(tr);
            });
            $('<td><a href="#" onclick="removeBtn(this,' + val['id'] + ')" title="Удалить"><i class="icon-trash"></i></a></td>').appendTo(tr);
            tr.appendTo('#mytable');
        });
    });
}

function removeBtn(obj, id) {
    if (confirm("Удалить контакт?")) {
        var parent = $(obj).parent().parent();
        $.getJSON('contacts?action=remove&id=' + id, function(data) {
            parent.fadeOut('slow', function() {
                $(this).remove();
            });
        });
    }
}

function addBtn() {
    $('form').submit(function() {
        var arr = $(this).serializeArray();
        $.getJSON('contacts?action=add&fio=' + arr[0].value + '&phone=' +
                arr[1].value + '&email=' + arr[2].value, function(data) {

            var tr = $('<tr></tr>');
            $('<td>' + data.id + '</td>').appendTo(tr);
            for (var i = 0; i <= 2; i++) {
                $('<td>' + arr[i].value + '</td>').appendTo(tr);
            }
            $('<td><a href="#" onclick="removeBtn(this,' + arr['id'] + ')" title="Удалить"><i class="icon-trash"></i></a></td>').appendTo(tr);
            tr.appendTo('#mytable');

        });
        $('#addcontact').modal('hide');
        return false;
    });
}

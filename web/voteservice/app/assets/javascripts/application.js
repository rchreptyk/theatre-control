// This is a manifest file that'll be compiled into application.js, which will include all the files
// listed below.
//
// Any JavaScript/Coffee file within this directory, lib/assets/javascripts, vendor/assets/javascripts,
// or any plugin's vendor/assets/javascripts directory can be referenced here using a relative path.
//
// It's not advisable to add code directly here, but if you do, it'll appear at the bottom of the
// compiled file.
//
// Read Sprockets README (https://github.com/rails/sprockets#sprockets-directives) for details
// about supported directives.
//
//= require jquery
//= require jquery_ujs
//= require turbolinks
//= require_tree .
//= require jquery
//= require bootstrap-sprockets

state = 'original'

function update()
{
    $.get('/state', function(data) {
        console.log(data)
        if(state != data.status || state == 'answered')
        {
            state = data.status
            renderState(state, data)
        }
    });
}

function renderState(state, data)
{
    if(state == 'idle')
    {
        $('#data').html("<strong>Please look here to push buttons when you're asked.</strong>")
    }
    else if(state == 'question')
    {
        $('#data').html('')
        data.answers.forEach(function(answer) {
            var button = '<button class="btn btn-default btn-lg">'+answer+'</button>'
            $('#data').append(button)
        });

        $('.btn').click(function() {
            var name = $('#name').html();
            var vote = $(this).html()

            url = '/vote/' + encodeURIComponent(vote) + '/' + encodeURIComponent(name)

            $.get(url, function(data) {
                console.log(data)
                renderState(state, data);
            });
        });
    }
    else if(state == 'answered')
    {
        $('#data').html('')
        data.results.forEach(function(answer) {
            $('#data').append('<strong>' + answer['name'] + '</strong>: ' + answer['vote'] + '<br />');
        });
    }
}

$(document).ready(function() {
    if($('#data').length)
    {
        update()
        setInterval(update, 1000);
    }
});

/**
 * Created by hf on 7/21/16.
 */
$.quake.comp.chosen = function (id, config) {
    $(function () {
        $('#' + id).append('<button id="' + id + '_btn" type="button" class="button"><div id="strs"></div></button>' +
            '<div style="display: none" class="' + id + '_btn">' +
            '<input type="text" id="' + id + '_input">' +
            '<button type="button" class="button alert" id="' + id + '_clear">清除</button><button type="button" class="button success" id="' + id + '_confirm">确认</button>' +
            '<div id="' + id + '_li"></div>' +
            '</div>');
        var html = '<ul>';
        for (var i in config.options) {
            html += '<li><input id="' + i + '" name="chosen" type="radio" class="inputs"><label title="' + config.options[i] + '" class="labels">' + config.options[i] + '</label></li>'
        }
        html += '</ul>';
        $('#' + id + '_li').append(html);
        var $inputs = $('.inputs');
        if (config.type == 'checkbox') {
            $inputs.attr('type', config.type);
        }
        $('.' + id + '_btn').width(config.row * 130 + 'px');
        //显示选中
        $inputs.change(function () {
            var strs = '';
            for (var i in $inputs) {
                if ($inputs[i].checked) {
                    strs += $inputs[i].id + ','
                }
            }
            thisStr = '';
            thisStr = strs;
            $('#strs').html('');
            $('#strs').append(strs);
        })
    });
    //缩放
    $('body').on('click', '#' + id + '_btn', function () {
        $('.' + id + '_btn').fadeToggle();
    });
    //点击确认
    $('body').on('click', '#' + id + '_confirm', function () {
        $('.' + id + '_btn').hide();
    });
    //过滤
    $('body').on('keyup', '#' + id + '_input', function () {
        var val = $('#' + id + '_input').val();
        var $labels = $('.labels');
        for (var i = 0; i < $labels.length; i++) {
            var title = $labels[i].title;
            if (title.indexOf(val) != -1) {
                $labels[i].parentNode.style.display = 'block';
            } else {
                $labels[i].parentNode.style.display = 'none';
            }

        }
    });
    //清除
    $('body').on('click', '#' + id + '_clear', function () {
            console.log($('#' + id + '_input'));
            // var inputVal = $('#' + id1 + '_input').val();
            var inputVal = $('#' + id + '_input').val();
            var split = thisStr.split(',');
            var newStr = [];
            var newStrs = '';
            if (inputVal.trim() != '') {
                var $labelsVis = $('.' + id + '_btn ul li:visible');
                console.log($labelsVis);
                for (var i = 0; i < $labelsVis.length; i++) {
                    var checkeds = $labelsVis[i].childNodes[0];
                    console.log(checkeds.checked);
                    console.log(checkeds.id);
                    if (checkeds.checked) {
                        split.splice(i, 1)
                    }
                }
                console.log(split);
                for (var j = 0; j < split.length; j++) {
                    newStr.push(split[j])
                }
                newStrs = newStr.toString();
                $('#strs').empty();
                $('#strs').append(newStrs);
                $('.' + id + '_btn ul li:visible input').attr('checked', false);
            } else {
                $('.inputs').attr('checked', false);
                $('#strs').empty();
            }
        }
    );
}
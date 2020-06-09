$( document ).ready(function() {
    changeImage();
    updateListSrc();
});

function readURL(input, id, isProduct = null) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function(e) {
            $('#' + id).attr('src', e.target.result);
            if (isProduct == true) {
                updateListSrc(); // only use product
            }
        }

        reader.readAsDataURL(input.files[0]); // convert to base64 string
    }
}

$("#input-img-icon").change(function() {
    readURL(this, 'img-icon');
    $("#img-icon").css("background", 'gray');
});

$("#input-img-slide").change(function() {
    readURL(this, 'img-slide');
});

$("#input-img-avatar").change(function() {
    readURL(this, 'img-avatar');
});

$("#input-img").change(function() {
    readURL(this, 'img-show');
});

function changeImage() {
    $(".input-img-pro").change(function () {
        strId = $(this).attr('id');
        var index = strId.replace("input-img-pro-", "");
        readURL(this, 'img-show-pro-' + index, true);
    });
}

$("#add-img").click(function() {
    var num = $('.show-image').length;
    var html = '                <div class="show-image" id="img-pro-' + num +'">\n' +
        '                            <div class="form-group row">\n' +
        '                                <input type="file" class="form-control-sm input-img-pro" id="input-img-pro-' + num +'" name="image[]">\n' +
        '                            </div>\n' +
        '                            <div class="form-group row display-img">\n' +
        '                                <img class="size-img-pro" src="#" id="img-show-pro-' + num +'" alt="Image">\n' +
        '                            </div>\n' +
        '                        </div>';

    if (num < 5) {
        $(".list-img").append(html);
        changeImage();
    }
});

$("#delete-img").click(function() {
    var num = $('.show-image').length;
    if (num > 1) {
        $('#img-pro-' + (num - 1)).remove();
    }
});

function updateListSrc() {
    $arrSrc = [];
    $( ".size-img-pro" ).each(function( index ) {
        $arrSrc.push($( this ).attr('src'));
    });
    $('#src-images').val($arrSrc);
}

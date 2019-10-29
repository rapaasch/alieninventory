$(document).ready(function(){

    $.ajax({
        type: 'GET',
        url: 'http://AUrlOrWhatever',
        success: function (response) {
            $("#items").empty();
            for (i = 0; i < response.length; i++) {
                var id = response[i].itemId;
                var name = response[i].itemName;
                var about = response[i].itemAbout;
                var quantity = response[i].quantity;
                var val = response[i].itemValue;
                var dateFound = response[i].dateFound;
                var locId = response[i].locId;

                var itemCard = '<div class="col-sm-3">';
                    itemCard += '<div class="card">';
                        itemCard += '<div class="card-header text-center">';
                            itemCard += '<img src="images/item' + id + '.jpg" class="card-img-top" alt="' + name + '">'
                            itemCard += '<a class="collapsed card-link" data-toggle="collapse" href="#collapse' + id + '">' + name + '</a>';
                        itemCard += '</div>';
                        itemCard += '<div id="collapse' + id + '" class="collapse" data-parent=".accordion">';
                            itemCard += '<div class="card-body">';
                                itemCard += '<p>' + about + '</p>';
                            itemCard += '</div>';
                        itemCard += '</div>'
                    itemCard += '</div>';
                $("items").append(itemCard);
            }
        }
    })

});
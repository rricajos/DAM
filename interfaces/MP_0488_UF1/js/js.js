var input_button;
var payment_select;
var consent;
var restore_button;
var print_button;


var total = 0;
var cart = {};

document.addEventListener("DOMContentLoaded", () => {

    inicializarVariables();
    establecerListeners();
    pay();
});


function inicializarVariables() {

    input_button = document.getElementById("input");
    payment_select = document.getElementById("payment");
    consent_checkbox = document.getElementById("consent");
    restore_button = document.getElementById("restore");
    print_button = document.getElementById("print");

}


function establecerListeners() {

    input_button.addEventListener("click", add);
    payment_select.addEventListener('change', pay);
    consent_checkbox.addEventListener('change', consent);
    restore_button.addEventListener("click", restore);
    print_button.addEventListener("click", print);
}



// funcitonalities

function add() {
    let a = validate_article();
    let b = validate_price();
    if (a && b) {

        insert_product()
        list_product();
        pay();
    }
}



// validations

function validate_article() {
    var article = document.getElementById("article");
    var article_error = document.getElementById("article_error");

    if (article.value === "") {
        article_error.innerHTML = "Falta artículo";
        article.focus();
        return false;

    } else {
        article_error.innerHTML = "";
        return true;
    }
}

function validate_price() {
    var price = document.getElementById("price");
    var price_error = document.getElementById("price_error");



    if (price.value === "") {
        price_error.innerHTML = "Falta precio";

        price.focus();
        return false

    } else {

        if (isNaN(price.value)) {
            price_error.innerHTML = " Tipo de dato incorrecto";
            price_error.style.display = "inline";
            price.focus();

        } else {
            price_error.style.display = "none";
            return true
        }
    }
}



// carrito 
function insert_product() {

    let article = document.getElementById("article").value;
    let price = document.getElementById("price").value;
    let cuantity = document.getElementById("cuantity").value;

    let ammount = price * cuantity;
    if (cart[article] == null) {
        cart[article] = ammount;
    } else {
        cart[article] += ammount;
    }
}


function list_product() {


    document.getElementById('articles').textContent = Object.keys(cart).join(',');
    document.getElementById('budget').textContent = Object.values(cart).reduce((a, b) => a + b, 0);

}


function pay() {


    var pay_detail_container = document.getElementById("payment_detail_container");
    const pay_select = payment_select;
    const pay_select_value = pay_select.options[pay_select.selectedIndex].value;

    console.log("okey")

    if (pay_select_value == "tarjeta") {




        let card_headline; // titular
        let card_number; // numero de tarjeta
        let card_code; // ccv - Código Valor de Validación o Verificación 

        // restet content
        pay_detail_container.innerHTML = ''

        // titular
        card_headline = document.createElement('input');
        card_headline.setAttribute('id', 'titular');
        card_headline.setAttribute('type', 'text');
        card_headline.setAttribute('class', 'text');
        card_headline.setAttribute('class', 'col-6 col-s-6');

        let card_headline_label = document.createElement('label');
        card_headline_label.setAttribute('for', 'titular');
        card_headline_label.innerHTML = 'Titular tarjeta ';
        card_headline_label.setAttribute('class', 'col-5 col-s-5');


        pay_detail_container.appendChild(card_headline_label);
        pay_detail_container.appendChild(card_headline);
        pay_detail_container.appendChild(document.createElement('br'));


        // numero de la tarjeta
        card_number = document.createElement('input');
        card_number.setAttribute('id', 'card_number');
        card_number.setAttribute('class', 'col-6 col-s-6');
        card_number.setAttribute('type', 'text');

        let card_number_label = document.createElement('label');
        card_number_label.setAttribute('for', 'card_number');
        card_number_label.setAttribute('class', 'col-5 col-s-5');

        card_number_label.innerHTML = 'Número tarjeta ';

        pay_detail_container.appendChild(card_number_label);
        pay_detail_container.appendChild(card_number);
        pay_detail_container.appendChild(document.createElement('br'));


        // codigo de verificacion
        card_code = document.createElement('input');
        card_code.setAttribute('id', 'card_code');
        card_code.setAttribute('class', 'col-6 col-s-6');
        card_code.setAttribute('type', 'text');

        let card_code_label = document.createElement('label');
        card_code_label.setAttribute('for', 'card_code');
        card_code_label.setAttribute('class', 'col-5 col-s-5');
        card_code_label.innerHTML = 'Código Valor de Validación';


        pay_detail_container.appendChild(card_code_label);
        pay_detail_container.appendChild(card_code);
        pay_detail_container.appendChild(document.createElement('br'));

    }

    if (pay_select_value == "efectivo") {

        // reset content
        pay_detail_container.innerHTML = '';



        label = document.createElement('label');
        label.setAttribute('for', 'importe');
        label.innerHTML = 'Importe efectivo: ' + Object.values(cart).reduce((a, b) => a + b, 0) + "€";;
        var sep = document.createElement('br');

        pay_detail_container.appendChild(label);
        pay_detail_container.appendChild(importe);
        pay_detail_container.appendChild(sep);
    }

    if (pay_select_value == "defecto" || pay_select_value == '') {
        pay_detail_container.innerHTML = '';
    }


}


function restore() {


    var inputs = document.getElementsByTagName('input');
    for (var key in inputs) {
        inputs[key].innerHTML = '';


    };

    var outputs = document.getElementsByTagName('output');
    for (var key in inputs) {
        outputs[key].innerHTML = '';

    };

    document.getElementById('cuantity').value = 1;
    document.getElementsByTagName('option')[0].selected = 'selected';
    cart = {};
    total = '';
    pay();
}



function consent() {

    var button_print = document.getElementById("print");

    if (consent_checkbox.checked) {
        button_print.disabled = false
        console.log("Checkbox is checked..");
    } else {
        button_print.disabled = true
        console.log("Checkbox is not checked..");
    }
}



function print() {


    var value = payment_select.options[payment_select.selectedIndex].value;
    if (value == 'defecto') {
        alert("Seleccione una forma de pago para continuar ")
    } else {

        var cart_keys = Object.keys(cart);
        var cart_values = Object.values(cart);
        var total = cart_values.reduce((partialSum, a) => partialSum + a, 0);
        alert(
            "Los artículos de la carta son: " + cart_keys.join(", ") +
            "\nEl precio total es: " + total + "€" +
            "\nForma de pago: " + value
        )
    }
};



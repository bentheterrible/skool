// Makes sure the page has loaded before the code executes.
window.onload = function () {

    // Finds the first element with an ID of 'nav_list' and adds
    // a 'is-hidden' class
    document.querySelector('#nav_list').classList.add('is-hidden');

    // Finds the first element with an ID  of 'nav_button' and
    // attaches a click event listener
    document.querySelector('#nav_button').onclick = function() {

        document.querySelector('#nav_list').classList.toggle('is-hidden');
            // classList.toggle() will add or remove a class from an element,
            // depending on whether or not it already exists.
    };
};

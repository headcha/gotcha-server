Handlebars.registerHelper("select", function(value, options) {
    return options.fn(this)
        .split('\n')
        .map(function(v) {
            var t = 'data-value="' + value + '"';
            var k = 'value="' + value + '"';

            if (RegExp(t , 'gi').test(v)) {
                return v.replace('>' , ' selected="selected">');
            } else if (RegExp(k , 'gi').test(v)) {
                return v.replace('>' , ' selected="selected">');
            } else {
                return v;
            }
        })
        .join('\n')
});

Handlebars.registerHelper('checked', function(currentValue) {
    return currentValue == true ? 'checked' : '';
});

Handlebars.registerHelper('dateFormat', function (value, block) {

    var f = block.hash.format || 'YYYY.MM.DD';
    f = f.toUpperCase();
    if (!value) return value;
    return moment(value).format(f);
});

Handlebars.registerHelper('attr-disabled', function(currentValue) {
    if(!currentValue)
        return "disabled";
});

Handlebars.registerHelper('equals', function(arg1, arg2, options) {
    return (arg1 == arg2) ? options.fn(this) : options.inverse(this);
});
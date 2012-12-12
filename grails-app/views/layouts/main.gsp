<html>
    <head>
        <title><g:layoutTitle default="Grails" /></title>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <r:require modules="jquery, css"/>

        <r:script type="text/javascript" disposition="head">
            function limitText(limitField, limitNum) {
                if (limitField.value.length > limitNum) {
                    limitField.value = limitField.value.substring(0, limitNum);
                }
            }

            function showInfoMessage(info) {
                var infoElement = $('#infoMessage');
                infoElement.text(info);
                if (infoElement.text().length > 0) {
                    infoElement.show();
                }
            }

            function rePaginate(id) {
                $.post('<g:resource dir="${controllerName}" file="rePaginate"/>', function(data) {
                    $('#' + id).html(data);
                });
            }

            /*  function updateList(id) {
             $.post('<g:resource dir="${controllerName}" file="updateList"/>', function(data) {
             $('#' + id).html(data);
             });
             }*/

        </r:script>

        <link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}">
        <link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">


        <r:layoutResources/>
    </head>
    <body>
    <div class="logo">
        <r:img uri="/images/dandelion.png" alt="Dandelion logo"/>
    </div>

        <g:layoutBody />
        <r:layoutResources/>
    </body>
</html>
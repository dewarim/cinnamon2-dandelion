<r:require module="codeMirror" />
<r:script type="text/javascript" disposition="head">
    var codeMirrorEditor;
    function createEditor(id) {
        codeMirrorEditor = CodeMirror.fromTextArea(id, {
            mode:'application/xml',
            lineNumbers:true
        });
        codeMirrorEditor.refresh();
    }
</r:script>
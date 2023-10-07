   function loadContent(url) {
            fetch(url)
                .then(response => response.text())
                .then(html => {
                    document.getElementById("mainArea").innerHTML = html;
                    const fileInput = document.getElementById('fileInput');
                        if (fileInput) { // Ensure the element exists
                            fileInput.addEventListener('change', function() {
                            const fileName = this.files[0] ? this.files[0].name : "";
                            document.getElementById('responseMessage').innerHTML = "Selected: "+fileName;
                        });
                    }
                });
        }

        window.onload = function() {
            loadContent('/search');
        }

    function performSearch() {
//    console.log("performSearch function called");
        var query = document.getElementById("query").value;
        fetch("/performSearch?query=" + encodeURIComponent(query))
            .then(response => response.json())
            .then(data => {
                // Displaying results as a simple list; modify as needed
                var resultsHTML = "<ul>";
                data.results.forEach(item => {
                    resultsHTML += "<li>" + item.token + " " + Object.keys(item.foundDoc).length + "</li>";
                });
                resultsHTML += "</ul>";
                document.getElementById("searchResults").innerHTML = resultsHTML;
            });
    }

    function performDirScan() {
//    console.log("performDirScan function called");
        var path = document.getElementById("path").value;
        fetch("/performDirScan?path=" + encodeURIComponent(path))
            .then(response => response.json())
            .then(data => {
                // Displaying results as a simple list; modify as needed
                var resultsHTML = "<ul>";
                data.forEach(item => {
                    resultsHTML += "<li>" + item + "</li>";
                });
                resultsHTML += "</ul>";
                document.getElementById("responseMessage").innerHTML = resultsHTML;
            });
    }

    function sendFile() {
        let fileInput = document.getElementById("fileInput");
        let checkbox = document.getElementById("replaceCheckbox");

        let file = fileInput.files[0];
        if (!file) {
            alert("Please select a file or directory before submitting.");
            return;
        }

        // Use FormData to send the file
        let formData = new FormData();
        formData.append("file", file);
        formData.append("replace", checkbox.checked);//document.getElementById("replaceCheckbox").checked

        fetch("/processFile", {
            method: 'POST',
            body: formData
        })
        .then(response => response.json())
        .then(data => {
            document.getElementById("responseMessage").innerText = data.message;
            fileInput.value='';
        })
        .catch(error => {
            document.getElementById("responseMessage").innerText = error;
            console.error("Error:", error);
        });
    }

 /*   END   */
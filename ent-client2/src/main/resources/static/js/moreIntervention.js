
var fileName = 'load'
var interventionId
function showAlert(element) {
    interventionId = element.getAttribute('data-id');
    console.log(element);
    console.log("ID de l'intervention : " + interventionId);

    // $('#downloads').text('Chargez le fichier');
    $('#float-input').val('Cher(e) étudiant(e), \n' +
        '      \n' +
        '      \tNous sommes heureux de vous informer que le traitement de votre demande d\'intervention a été terminé avec succès.\n\n' +
        '      \tNous espérons que le problème pour lequel vous avez demandé de l\'aide a été résolu de manière satisfaisante. \n' +
        '      \tSi vous avez d\'autres questions ou préoccupations, n\'hésitez pas à nous contacter à tout moment.\n' +
        '      Merci pour votre collaboration.\n\n\n' +
        '      Cordialement,\nLe département \n' +
        '      INSTITUT UNIVERSITAIRE SAINT JEAN');



}
function repondreIntervention(idIntervention, dataS) {
    console.log(idIntervention, dataS)
    $.ajax({
        url: 'http://localhost:9090/api/interventions/Termine/' + idIntervention,
        type: 'PUT',
        data: dataS,
        processData: false, // empêche jQuery de transformer les données en chaîne de requête
        contentType: false,
        success: function (data) {
            console.log('Réponse de l\'API:', data);
            Swal.fire({
                title: "Felicitation",
                html: "Nous sommes heureux de vous annocer que la demande d'intervention a ete finalisee avec succes",
                hideOnOverlayClick: false,
                hideOnContentClick: false,
                showDenyButton: false,
                showCancelButton: false,
                confirmButtonText: "Continuer",
            }).then((result) => {
                if (result.isConfirmed) {
                    window.location.href = 'http://localhost:5001/listeIntervention';
                }
            });
        },
        error: function (xhr, status, error) {
            console.error('Erreur lors de la requête API:', status, error);
        }
    });
}

// $('#files').change(function (event) {
//     loadFileS(event);
// });

var selectedFiles
function loadFileS(event) {
    selectedFiles = event.target.files;
    console.log(selectedFiles);

    if (selectedFiles.length > 0) {
        fileName = selectedFiles[0].name + " ' et " + (selectedFiles.length - 1) + " autres fichier(s)'";
        console.log(fileName);

        $('#downloads').text(fileName);
        // Vous pouvez utiliser la variable fileName comme nécessaire ici
    }
}


function envoyerR() {
    var DescriptionIntervention = document.getElementById('float-input').value;
    var interventionId = document.getElementById('id-finalize-int').value;

    if (DescriptionIntervention == '') {
        Swal.fire({
            title: "Données Absentes",
            html: "Vous devez renseigner une <b>Description</b> et éventuellement une <b>Pièce Jointe</b>.",
            confirmButtonText: "Continuer",
            showDenyButton: false,
            showCancelButton: false,
            hideOnOverlayClick: false,
            hideOnContentClick: false
        });
    }
    else {
        Swal.fire({
            title: "Êtes-vous sûr(e)?",
            html: "Souhaitez vous réelement finaliser cette demande d'intervention ?",
            showDenyButton: true,
            showCancelButton: false,
            confirmButtonText: "Continuer",
            denyButtonText: `Annuler`
        }).then((result) => {
            if (result.isConfirmed) {
                var formData = new FormData();
                formData.append('emailContent', DescriptionIntervention);

                for (var key in selectedFiles) {
                    if (selectedFiles.hasOwnProperty(key)) {
                        var file = selectedFiles[key];
                        formData.append('piecesJointes', file);
                        console.log(file)
                    }
                }
                console.log(interventionId)
                repondreIntervention(interventionId, formData)

            } else if (result.isDenied) { }
        });
    }

}
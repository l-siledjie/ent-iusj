// var token = '[[${accessToken}]]'
var fileName = 'load'
var fichier = ''
var listeObjets = [];


// Appeler la fonction pour charger les options lors du chargement de la page
$(document).ready(function () {
    listeIntervention();
});


// Attacher un gestionnaire d'événement à l'élément select
$('#intervention').change(
    function () {
        // Récupérer la valeur sélectionnée
        var idCategorie = $(this).val();
        // Afficher la valeur dans la console (vous pouvez faire autre chose avec cette valeur)
        console.log("La valeur sélectionnée est : " + idCategorie);
        listesousIntervention(idCategorie)
    }

);


$('#file').change(function (event) {
    loadFile(event);
});



function chargerOptionsSelectIntervention(liste, id) {
    var select = $(id);
    // Effacer les options existantes
    // select.empty();
    // Ajouter les nouvelles options à partir de la liste d'objets
    $.each(liste, function (index, objet) {
        select.append('<option value="' + objet.idCategorie + '">' + objet.intituleCategorie + '</option>');
    });
}
function chargerOptionsSelectSIntervention(liste, id) {
    var select = $(id);
    // Effacer les options existantes
    select.empty();
    // Ajouter les nouvelles options à partir de la liste d'objets
    $.each(liste, function (index, objet) {
        select.append('<option value="' + objet.idSousIntervention + '">' + objet.intituleSousIntervention + '</option>');
    });
}
function listeIntervention() {
    $.ajax({
        url: 'http://localhost:9090/api/interventions/categorie/Liste',
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            listeObjets = data
            console.log('Réponse de l\'API:', data);
            chargerOptionsSelectIntervention(listeObjets, '#intervention');
            // Vous pouvez manipuler les données ici
        },
        error: function (xhr, status, error) {
            console.error('Erreur lors de la requête API:', status, error);
        }
    });
}
// var accessToken = ''  ;
function listesousIntervention(idCategorie) {
    $.ajax({
        url: 'http://localhost:9090/api/interventions/categorie/sousIntervention/' + idCategorie,
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            listeObjets = data
            console.log('Réponse de l\'API:', data);
            chargerOptionsSelectSIntervention(listeObjets, '#sous-intervention');
            // Vous pouvez manipuler les données ici
        },
        error: function (xhr, status, error) {
            console.error('Erreur lors de la requête API:', status, error);
        }
    });
}
function interventionbyId(id) {
    $.ajax({
        url: 'http://localhost:9090/api/interventions/' + id,
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            console.log('Réponse de l\'API:', data);
            // Vous pouvez manipuler les données ici
        },
        error: function (xhr, status, error) {
            console.error('Erreur lors de la requête API:', status, error);
        }
    });
}
function afficherIntervention(intervention) {
    console.log(intervention)
}

// var accessToken = '
var selectedFiles
function loadFile(event) {
    var selectedFiles = event.target.files;
    fichier = selectedFiles;

    if (selectedFiles.length > 0) {
        var fileType = selectedFiles[0].type.toLowerCase();
        var allowedTypes = ['application/pdf', 'image/png', 'image/jpeg', 'image/jpg'];

        if (allowedTypes.indexOf(fileType) === -1) {
            Swal.fire({
                title: "Piece Jointe Invalide.",
                html: "Nous sommes desole de vous l'annoncer mais seuls les fichiers <b> PDF </b> et les images (<b> PNG, JPG, JPEG </b>) sont autorisés.",
            }).then(() => {
                $('#file').val(""); // Efface le champ de fichier pour supprimer la sélection incorrecte.
                $('#download').text("Selectionnez la PieceJointe.");
            });
        } else {
            var fileName = selectedFiles[0].name;
            if (selectedFiles.length > 1) {
                fileName += " ' et " + (selectedFiles.length - 1) + " autres fichier(s)'";
            }
            console.log(fileName);
            $('#download').text(fileName);
        }
    }
}



function loadFile1(event) {
    selectedFiles = event.target.files;
    return selectedFiles;
}



function poserintervention(codeEtudiant, idCategorie, dataS) {
    var url = 'http://localhost:9090/api/interventions/soumettre/' + codeEtudiant + '/' + idCategorie;

    $.ajax({
        url: url,
        type: 'POST',
        data: dataS,
        processData: false, 
        contentType: false,
        success: function (data) {
            Swal.fire({
                title: "Felicitation",
                html: "Nous sommes heureux de vous annocer que votre demande d'intervention a bien ete soumise",
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




// Fonction pour gérer le changement de fichier
function handleFileChange(e) {
    const selectedFiles = e.target.files;
    piecesJointes = selectedFiles;
    console.log("Nanyang Brice :", selectedFiles);
    console.log("Nanyang Brice :", piecesJointes);
}

// Attacher le gestionnaire d'événements au chargement du document
$(document).ready(function () {
    // Attacher la fonction handleFileChange à l'événement change du champ de fichier
    $('#envoyer').on('click', function (e) {
        var matricule = $('#matricule').val();
        var intervention = $('#intervention').val();
        var idSousIntervention = $('#sous-intervention').val();
        var DescriptionIntervention = $('#exampleInputUsername1').val();

        if (intervention === '' || idSousIntervention === '' || DescriptionIntervention === '') {
            Swal.fire({
                hideOnOverlayClick: false,
                hideOnContentClick: false,
                title: "Données Absentes",
                text: "Vous devez remplir tous les champs avant de soumettre le formulaire.",
                confirmButtonText: "Continuer",
            });
        } else {
            var data = {
                intervention: intervention,
                idSousIntervention: idSousIntervention,
                DescriptionIntervention: DescriptionIntervention,
                pieceJointe: []
            };

            for (var i = 0; i < fichier.length; i++) {
                data.pieceJointe.push(fichier[i]);
                console.log(fichier[i]);
            }
            var jsonData = JSON.stringify(data);

            Swal.fire({
                hideOnOverlayClick: false,
                hideOnContentClick: false,
                title: "Êtes-vous sûr(e)?",
                html: "Souhaitez-vous réellement soumettre cette demande d'intervention ?",
                showDenyButton: true,
                confirmButtonText: "Continuer",
                denyButtonText: "Annuler"
            }).then((result) => {
                if (result.isConfirmed) {
                    poserintervention(matricule, intervention, jsonData);
                }
            });
        }
    });
});



function getInfosDescription() {
    var description = $('#exampleInputUsername1').val();
    if (description.length < 100) {
        $('#text-description').text("Vous avez entrez : " + description.length + "/100 caractères");
    } else {
        $('#exampleInputUsername1').val(description.substr(0, 99));
        $('#text-description').text(description.length + "/100 caractères");
    }
}
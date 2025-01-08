function constructFileDownload(params) {
    var down = `<a th:href="@{/download/{idPieceJ}(idPieceJ=${params})}"><i class="bi bi-file-earmark-binary-fill text-primary"></i></a>`;
    return down
}

function showInterventionDetail(params) {
   
    $.ajax({
        url: "http://localhost:9090/api/interventions/one/etudiant/"+params,
        method: "GET",
        dataType : "json",
    }).done(function(response){
        $("#modalIntervention").text(response.idIntervention);
        $("#modalObjet").text(response.sousIntervention.intituleSousIntervention);
        $("#modalMatriculeEtudiant").text(response.etudiant.matricule);
        $("#modalNomEtudiant").text(response.etudiant.nomEtudiant.toUpperCase() + ' ' + response.etudiant.prenomEtudiant);
        $("#modalDepartement").text(response.sousIntervention.departement.intituleDepartement);
        $("#modalCategorie").text(response.sousIntervention.categorie.intituleCategorie);
        $("#modalDateEnvoie").text(new Date(response.dateCreationInter).toISOString());
        $("#modalStatutDemande").text(response.statut);
        $("#modalDateModif").text(response.dateModificationInter == null ? "" : new Date(response.dateModificationInter).toISOString());
        $("#modalDescription").val(response.description);
        $("#modalPieceJointe").html(response.piecesJointes.idPieceJointe != "" ? constructFileDownload(response.piecesJointes.idPieceJointe) : "Aucune Piece Jointe");
        $("#modalResponsableTraitement").text(formatterEmail(response.personnel.nomPersonnel.toUpperCase() + ' ' + response.personnel.prenomPersonnel) );
        console.log(response);
    })
}

function formatterEmail(email) {
    let emailTronque = email.substring(0, 25);
    if (email.length > 25) {
        emailTronque += '...';
    }
    return emailTronque;
}



function finalisation(params) {
    $("#id-finalize-int").val(params)
}

function cancelInterventionRequest(params) {
    const path = "http://localhost:9090/api/interventions";
    
    $.ajax({
        url: `${path}/one/etudiant/${params}`,
        method: "GET",
        dataType: "json",
    }).done((response) => {
        const etudiant = response.etudiant;
        const confirmationMessage = `Souhaitez-vous réellement rejeter la demande d'intervention de l'étudiant <b>${etudiant.nomEtudiant.toUpperCase()} ${etudiant.prenomEtudiant}</b>?`;

        Swal.fire({
            title: "Êtes-vous sûr(e)?",
            html: confirmationMessage,
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "Continuer",
            closeOnConfirm: false
        }).then(() => {
            return $.ajax({
                url: `${path}/cancel/${response.idIntervention}`,
                type: "POST",
                contentType: "application/json; charset=utf-8",
                cache: false
            });
        }).then(() => {
            Swal.fire({
                title: 'Félicitation',
                html: "Nous vous informons que cette demande d'intervention a bien été rejetée.",
                confirmButtonColor: "#3085d6",
                confirmButtonText: "Continuer",
            }).then(() => location.reload());
        }).catch((error) => {
            Swal.fire({
                title: 'Action Impossible',
                html: "Il semblerait que cette intervention a déjà été prise en charge par conséquent elle ne peut pas être annulée pour l'instant. Veuillez réessayer plus tard.",
                confirmButtonColor: "#3085d6",
                confirmButtonText: "Continuer",
            });
        });
    }).fail((error) => {
        console.error("An error occurred while fetching etudiant intervention:", error);
    });
}


function prendreChargeInterventionRequest(params, id) {
    const path = "http://localhost:9090/api/interventions";
    const getEtudiantIntervention = $.ajax({
        url: `${path}/one/etudiant/${params}`,
        method: "GET",
        dataType: "json",
    });

    getEtudiantIntervention.done((response) => {
        const etudiant = response.etudiant;
        const confirmationMessage = `Souhaitez-vous réellement prendre en charge la demande d'intervention de l'étudiant <b>${etudiant.nomEtudiant.toUpperCase()} ${etudiant.prenomEtudiant}</b>?`;

        Swal.fire({
            title: "Êtes-vous sûr(e)?",
            html: confirmationMessage,
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "Continuer",
            closeOnConfirm: false
        }).then(() => {
            const prendreEnChargeRequest = $.ajax({
                url: `${path}/prendre-en-charge/${response.idIntervention}/${id}`,
                type: "PUT",
                contentType: "application/json; charset=utf-8",
                cache: false
            });

            return prendreEnChargeRequest;
        }).then(() => {
            Swal.fire({
                title: 'Félicitation',
                html: "Nous vous informons que cette demande d'intervention a bien été prise en charge.",
                confirmButtonColor: "#3085d6",
                confirmButtonText: "Continuer",
            }).then(() => location.reload());
        }).catch((error) => {
            Swal.fire({
                title: 'Erreur',
                html: "Des erreurs sont survenues lors de la prise en charge de cette demande d'intervention",
                confirmButtonColor: "#3085d6",
                confirmButtonText: "Continuer",
            });

            console.error("An error occurred:", error);
        });
    }).fail((error) => {
        console.error("An error occurred while fetching etudiant intervention:", error);
    });

    console.log(params, id);
}




function finaliserInterventionRequest(params) {
    var path = "http://localhost:9090/api/interventions";
    $.ajax({
        url: path +"/one/etudiant/"+params,
        method: "GET",
        dataType : "json",
    }).done(function(response){
        Swal.fire({
            title: "Êtes vous sûres?",
            html: "Souhaitez vous réelement rejeter la demande d'intervention de l'etudiant <b> " + response.etudiant.nomEtudiant.toUpperCase() + " " + response.etudiant.prenomEtudiant + " </b> ?",
            hideOnOverlayClick: false,
            hideOnContentClick: false,
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "Continuer",
            closeOnConfirm: false
        }).then(() => {
            return $.ajax({
                url: "http://localhost:9090/api/interventions/cancel/"+response.idIntervention,
                type: "POST",
                contentType: "application/json; charset=utf-8",
                cache: false
            })
        }).then((response) => {
            Swal.fire({
                hideOnOverlayClick: false,
                hideOnContentClick: false,
                title: 'Félicitation',
                html: "Nous vous informons que  cette demande d'intervention a bien ete rejetee.",
                confirmButtonColor: "#3085d6",
                confirmButtonText: "Continuer",
            }).then(function (result) {
                if (result.isConfirmed) {
                    location.reload();
                }
            });
        }).catch((error) => {
            Swal.fire({
                hideOnOverlayClick: false,
                hideOnContentClick: false,
                title: 'Erreur',
                html: "Des erreurs sont survenue durant le rejet de cette demande d'intervention",
                confirmButtonColor: "#3085d6",
                confirmButtonText: "Continuer",
            })
            console.error("An error occurred:", error);
        });
    })
}
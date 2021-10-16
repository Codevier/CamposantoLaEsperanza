$(function () {
    $('#btn-actionb').click(function (e) {
        buscador();
    });
    const buscador = () => {

        var cedula = document.getElementById("txt-cede").value;
        $.ajax({
            cache: false,
            type: "POST",
            url: "Controllerbuscarpaciente",
            data: {cedula: cedula},
            dataType: 'json',
            error: function (request, status, error)
            {
                alert(request, status, error);
            },
            success: function (data)
            {
                console.log(data);
                $("#txt-cedi").val(data.paciente_dni);
                $("#txt-namen").val(data.paciente_primer_segundo_nombre);
                $("#txt-lnamea").val(data.paciente_primer_segundo_apellido);
                $("#txt-namef").val(data.paciente_grupo);
                $("#txt-lnamet").val(data.paciente_convencional);
                $("#txt-cel").val(data.paciente_telefono);
                 $("#txt-provin").val(data.paciente_provincia);
                $("#txt-direccion").val(data.paciente_direccion);
                $("#txt-genero").val(data.paciente_genero);
                $("#txt-ayudaeconomica").val(data.paciente_ayudasocial);
                 $("#txt-nacionalidad").val(data.paciente_nacionalidad);
                $("#txt-tiposangre").val(data.paciente_correoelectronico); 
            }

        });
    };
});




$(document).ready(function() {
    $("#btnTrabajo").click(function(){
        $.ajax({
			url: 'Usuarios',
			type: 'post',
			data: { 
                                user:"",
                                bress:""
                        },
			dataType: 'json',
			success: function (data) {    				
					$.each(data, function (index, record) {
						if ($.isNumeric(index)) { 
							var row = $("<tr />");
							$("<td />").text(record.id).appendTo(row);
                                                        $("<td />").text(record.nombre).appendTo(row);
                                                        $("<td />").text(record.apaterno).appendTo(row);
                                                        $("<td />").text(record.amaterno).appendTo(row);
                                                        $("<td />").text(record.colonia).appendTo(row);
                                                        $("<td />").text(record.telefono).appendTo(row);
                                                        $("<td />").text(record.correo).appendTo(row);
                                                        $("<td />").text(record.usuario).appendTo(row);
                                                        $("<td />").text(record.password).appendTo(row);
                                                        $("<td />").text(record.rol).appendTo(row);
                                                        $("<td />").html("<button id='btnElimina"+record.id+"'onclick=\"elimnar("+record.id+");\" >Eliminar</button>").appendTo(row);
							row.appendTo("table");
						}
                                                else{
                                                    alert("No es numerico");
                                                }
					})//fin de each
				

				$('table').DataTable({
                                        
					"bJQueryUI": true,
					"sPaginationType": "full_numbers"                                     
                                        
				});//fin de datatable
			}//fin de data succes
		});//fin de ajax
                $("#btnTrabajo").hide();
        });/**Fin click**/
        
        /**************************Evento clik  de boton modal*************************************************/
        $("#btnModal").click(function(){
           $('#myModal').modal();
        });
        /**************************Final del evento click del boton modal**************************************/
        
        
        /****************Evento cliok de modal para agregar datos************************************************************/
        $("#btnAgregarRegistros").click(function(){
            
            //Validamos todo los campos
            if($("#txtnombre").val()!="")
            {
                $("#txtnombre").addClass("blanco");
                if($("#txtpaterno").val()!="")
                {
                    $("#txtpaterno").addClass("blanco");
                    if($("#txtmaterno").val()!="")
                    {
                        $("#txtmaterno").addClass("blanco");
                        if($("#txtcolonia").val()!="")
                        {
                            $("#txtcolonia").addClass("blanco");
                            if($("#txttelefono").val()!="")
                            {
                                $("#txttelefono").addClass("blanco");
                                if($("#txtcorreo").val()!="")
                                {
                                    $("#txtcorreo").addClass("blanco");
                                    if($("#txtusuario").val()!="")
                                    {
                                        $("#txtusuario").addClass("blanco");
                                        if($("#txtpasword").val()!="")
                                        {
                                            $("#txtpasword").addClass("blanco");
                                            if($("#txtdomicilio").val()!="")
                                            {
                                                $("#txtdomicilio").addClass("blanco");
                                                var nombreVar = $('#txtnombre').val();
                                                var apaternoar = $('#txtpaterno').val();
                                                var amateroVar = $('#txtmaterno').val();
                                                var domicilioVar = $('#txtdomicilio').val();
                                                var coloniaVar = $('#txtcolonia').val();
                                                var telefonoVar = $('#txttelefono').val();
                                                var correoVar = $('#txtcorreo').val();
                                                var usuarioVar = $('#txtusuario').val();
                                                var passVar = $('#txtpasword').val();

                                                // Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
                                                $.post('Registrar', {
                                                    nombre: nombreVar,
                                                    apaterno: apaternoar,
                                                    amaterno: amateroVar,
                                                    domicilio: domicilioVar,
                                                    colonia: coloniaVar,
                                                    telefono: telefonoVar,
                                                    correo: correoVar,
                                                    usuario: usuarioVar,
                                                    pass: passVar
                                                }, function (responseText) {                
                                                    alert(responseText);

                                                    $('#txtnombre').val('');
                                                    $('#txtapaterno').val('');
                                                    $('#txtAMaterno').val('');
                                                    $('#txtDomiciolio').val('');
                                                    $('#txtColonia').val('');
                                                    $('#txttelefono').val('');
                                                    $('#txtcorreo').val('');
                                                    $('#txtUsuario').val('');
                                                    $('#txtpassword').val('');
                                                });
                                            }
                                            else
                                            {
                                                alert("Favor de validar el campo password");
                                                $("#txtpasword").addClass("rojito");
                                                $("#txtpasword").focus();
                                            }
                                        }
                                        else
                                        {
                                            alert("Favor de validar el campo password");
                                            $("#txtpasword").addClass("rojito");
                                            $("#txtpasword").focus();
                                        }
                                    }
                                    else
                                    {
                                        alert("Favor de validar el campo usuario");
                                        $("#txtusuario").addClass("rojito");
                                        $("#txtusuario").focus();
                                    }
                                }
                                else
                                {
                                    alert("Favor de validar el campo correo");
                                    $("#txtcorreo").addClass("rojito");
                                    $("#txtcorreo").focus();
                                }
                            }
                            else
                            {
                                alert("Favor de validar el campo colonia");
                                $("#txtcolonia").addClass("rojito");
                                $("#txtcolonia").focus();
                            }
                        }
                        else
                        {
                            alert("Favor de validar el campo colonia");
                            $("#txtcolonia").addClass("rojito");
                            $("#txtcolonia").focus();
                        }
                    }
                    else
                    {
                        alert("Favor de validar el campo materno");
                        $("#txtmaterno").addClass("rojito");
                        $("#txtmaterno").focus();
                    }
                }
                else
                {
                    alert("Favor de validar el campo paterno");
                    $("#txtpaterno").addClass("rojito");
                    $("#txtpaterno").focus();
                }
            }
            else
            {
                alert("Favor de validar el campo nombre");
                $("#txtnombre").addClass("rojito");
                $("#txtnombre").focus();
            }
            
        });
        /***************Fin evento click de madal para agregar datos*********************************************************/
        $("#btnEjemplo").click(function(){
           alert("Hola"); 
           var t = $("#tbldatos").DataTable();
           
        });
        
        
       
        
    });
/****************************Fin del document ready    ***************************************************/    

/****************************Inicion de hola**************************************************************/
function elimnar(dato)
{
    
    var t = $("#tbldatos").DataTable();
    /*var ts = t;*/
    var datomando=dato;
    $.ajax({
        url:'EliminaUsuario',
        type:'post',
        data:{
            idValor:datomando
        },
        dataType:'text',
        success:function(data)
        {
            //area de trabajo
            alert(data);
            /*Borramos tods los datos*/
            var oSettings = $('#tbldatos').dataTable().fnSettings();
            var iTotalRecords = oSettings.fnRecordsTotal();
            for (i=0;i<=iTotalRecords;i++) {
                $('#tbldatos').dataTable().fnDeleteRow(0,null,true);
            }
            
            /*Agregamos nuevamente la informacion a ala base datos*/
            nuevamenteLlenarTabla();
        }
        
    });
}
/****************************Fin de hola*******************************************************************/

var nuevamenteLlenarTabla = function()
{
     $.ajax({
			url: 'Usuarios',
			type: 'post',
			data: { 
                                user:"",
                                bress:""
                        },
			dataType: 'json',
			success: function (data) {    				
					$.each(data, function (index, record) {
						if ($.isNumeric(index)) { 
							var row = $("<tr />");
							$("<td />").text(record.id).appendTo(row);
                                                        $("<td />").text(record.nombre).appendTo(row);
                                                        $("<td />").text(record.apaterno).appendTo(row);
                                                        $("<td />").text(record.amaterno).appendTo(row);
                                                        $("<td />").text(record.colonia).appendTo(row);
                                                        $("<td />").text(record.telefono).appendTo(row);
                                                        $("<td />").text(record.correo).appendTo(row);
                                                        $("<td />").text(record.usuario).appendTo(row);
                                                        $("<td />").text(record.password).appendTo(row);
                                                        $("<td />").text(record.rol).appendTo(row);
                                                        $("<td />").html("<button id='btnElimina"+record.id+"'onclick=\"elimnar("+record.id+");\" >Eliminar</button>").appendTo(row);
							row.appendTo("table");
						}
                                                else{
                                                    alert("No es numerico");
                                                }
					})//fin de each
				

				$('table').DataTable({
                                        
					"bJQueryUI": true,
					"sPaginationType": "full_numbers"                                     
                                        
				});//fin de datatable
			}//fin de data succes
		});//fin de ajax
}
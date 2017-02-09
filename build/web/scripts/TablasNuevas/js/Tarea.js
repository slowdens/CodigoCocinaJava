/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
    
        $('#tbldatos').DataTable( {
            "processing": true,
            "serverSide": true,
            "ajax": "Usuarios",            
             "columns": [
              
                { "data": "id" },
                { "data": "nombre" },
                { "data": "apaterno" },
                { "data": "amaterno" },
                { "data": "colonia" },
                { "data": "telefono" },
                { "data": "correo" },
                { "data": "usuario" },
                { "data": "password" },
                { "data": "rol" }
            ]
            
            
        });
       
});/*Termina el ready*/

<h1 align="center"> Api Recordatorio</h1>

<h2 align="left"> Descripcion</h2>
<p>
la idea de esta api es que la persona pueda registrar cualquier tarea o
accion que desee recordar ademas de ver sus recordatorios modificarlo, etc.</p>
<h2 align="left"> Arquitectura</h2>
<p>
para esta api se utilizo arquitectura limpia, la idea es que sea modular entoces 
el login tiene sus 4 capas wed(presentaciòn), application, domain y infrastructure,
asi como el de recordatorio, tambien tiene autenticacion con jwt y tanto este servicio como el ClaRegistroUsuario 
tienen el mismo tipo de cifrado de manera que aunque se utilizan dos lenguajes 
diferentes el cifrado y descifrado de las contraseñas sea el mismo.
</p>
<h2 align="left"> Tecnologias utilizadas</h2>
<p>
    <li>java 21</li>
    <li>sprint framework</li>
    <li>jakarta.persistence</li>
    <li>JWT</li>
    <li>Jpa</li>
</p>
<h2 align="left"> Version del aplicativo</h2>
<p> V.0.0.1</p>
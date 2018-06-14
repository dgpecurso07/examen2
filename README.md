# examen2

1. Iniciar rancher y contenedores

El rancher se inicia de la siguiente manera:

$sudo docker run –d –restar=unless-stopped –p 8080:8080 rancher/server

Después de un breve tiempo de espera en lo que levanta el servidor, podemos ingresar al rancher desde nuestro navegador introduciendo la ip del servidor con el puerto 8080.

Se configuran los permisos de usuario, desde el menú admin -> Access Control, se elige local y se ingresa el usuario y contraseña.

Ingresamos al menú Infrastructure > Hosts
 
Verificamos que el host este configurado con la IP y damos clic en Save
 
En un servidor Linux, diferente al en el que estamos corriendo el rancher vamos a levantar un contenedor escribiendo en la línea de comandos la instrucción que aparece en la línea 5 y damos close.

El paso anterior se repite un n numero de veces segun el numero de contenedores que se deseen. (En un servidor linux diferente en cada ocasion)

2. Dar de alta servicio

Vamos a agregar el servicio del docker que creamos, ingresamos al menú Default > Default
 
Agregamos un servicio dándole un nombre en el rancher para identificarlo por lo que en "name" ponemos "examen2srv" y en “Select Image” escribimos el nombre del docker que subimos al Docker Hub que es "dgpecurso07/examen2".
 
Seleccionamos en User = None
 
Y en la sección Security/Host  indicamos que el LogDriver será json-file
 
Damos clic en create y esperamos que el servicio este activo.

3. Dar de alta balanceador

Damos clic en add sevice y load balancer.

Le ponemos el nombre en "name" = "examen2balancer"

 indicamos el puerto del balanceador en "Port"=10001, elegimos el servicio que acabamos de dar de alta "Target"="Default/examen2srv" e indicamos el puerto en el que el servicio se levanta por defecto "Port"=8080.
 
Damos clic en create y podemos ver al balanceador activándose.

Al dar clic en el i del servicio podemos escalarlo n vecer

4. Probar

Ingresamos al menú Infrastructure > Hosts y podemos ver los contenedore con el servicio desplegado en ellos las n veces que lo escalamos.

Para probar hay que verificar en la ip de que contenedor quedo el balanceador y acceder por un navegador de la siguiente manera:

http://ipbalanceador:10001/api/factorial?num=5








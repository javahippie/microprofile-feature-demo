# Starting the network

You need to add some hostnames to your local hosts file for the network to work (e.g. /etc/hosts on Mac):
* jaeger
* prometheus
* keycloak
* inventory
* pricing

Keycloak takes some minutes to start, wait for a success message, here

# Starting the application
Run `mvn liberty:dev` from the folder 'checkout', you are good to go!

# Obtaining a Token
You can get a Token with a POST request to this address: http://keycloak:8080/auth/realms/MicroProfile/protocol/openid-connect/token
Add the following FORM parameters to the request:

* client_id: MP-Rest
* username: admin
* password: password
* scope: email
* grant_type: client_credentials
* client_secret: 7170a162-f0f8-4bd5-b04f-ebd5effdcdaf

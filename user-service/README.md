# [Quarkus](https://quarkus.io/) Demo Project With Docker Image

1. Download [GraalVM Community Edition 1.0 RC16](https://github.com/oracle/graal/releases) , export GRAALVM_HOME and add $GRAALVM_HOME/bin to path

      export GRAALVM_HOME=/your_folder/graalvm/Contents/Home

      export PATH=${PATH}:$GRAALVM_HOME/bin

 
2. Create docker native runnable application

     ./mvnw package -Pnative -Dnative-image.docker-build=true

3. Build docker image

      docker build -t quarkus-docker-native .
     
4. Run your docker image and test from browser [http://localhost:8080/hello](http://localhost:8080/hello)

      docker run -i --rm -p 8080:8080 quarkus-docker-native
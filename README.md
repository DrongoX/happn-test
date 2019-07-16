Usage: 

Run the application, for example using Gradle SpringBoot plugin: `./gradlew bootRun`

Access the API via your browser, Postman, or just curl:

`curl http://localhost:8080/worldmesh/poicount\?min_lat\=6.5\&min_lon\=-7`

`curl http://localhost:8080/worldmesh/heaviest\?amount\=3`



Implementation notes:
1. For the `poicount` enpoint, the input is not limited to have 0.5 precision. 
Any valid coordinate will work.

2. For invalid coordinates input, server will respond with HTTP 400. 

3. for `heaviest` endpoint, if, for example, the service was asked top-3 areas, 
but we have 4+ areas of same top weight, there is no guarantee which 3 will be selected,
as it is not precised in the task.

4. TSV-to-Object transformation is a bit ugly, but that was not the key point of the exercise. 
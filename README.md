## Usage: 

Run the application, for example using Gradle SpringBoot plugin: `./gradlew bootRun`

Access the API via your browser, Postman, or just use curl:

`curl http://localhost:8080/worldmesh/poicount\?min_lat\=6.5\&min_lon\=-7`

`curl http://localhost:8080/worldmesh/heaviest\?amount\=3`



## Implementation notes:
0. In the task it was asked to provide a good solution on performance side, but no details were given:
either it is a CPU performance (response time), or memory, etc. 
For example, it is not precised how many lines should I expect in the input file.

So, the POI data (`WorldMesh`) is read from the repository (and so from the file) on each call. 
Knowing the file is not supposed to get changed during the application run, we could have cached the result.
We could add caching at repository level, or just simply transform `WorldMesh` to the Bean singletone, 
read it once on the startup, and throw away the repo. That would avoid re-reading of the file.

I've left the solution with repository, because, in my opinion, 
for the PoC purposes there is no much trouble in reading the file. 
PoC is not supposed to run on the file with millions of records, I guess, 
but current option is easier upgradable to start using real data storage. 
My repo kinda simulates the requests to the DB, which role is played by the file. 

Worth to say, TSV-to-Object transformation is a bit ugly, but that was not the key point of the exercise. 

1. For the `poicount` enpoint, the input is not limited to have 0.5 precision. 
Any valid coordinate will work.

2. For invalid coordinates input, server will respond with HTTP 400. 

3. For `heaviest` endpoint, if, for example, the service was asked top-3 areas, 
but we have 4+ areas of same top weight, there is no guarantee which 3 areas will be selected,
as it is not precised in the task.
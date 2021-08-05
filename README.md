# unit test
To build and run unit test: mvn test

# run
To run it: mvn clean install

# api endpoint
Post /transformers
{"type":"D","name":"Predaking","strength":8,"endurance":6,"overallRating":31,"rank":7,"speed":2,"firepower":6,"courage":5,"intelligence":9,"skill":10}

Get /transformers

Put /transformers
{"type":"D","name":"Predaking","strength":8,"endurance":6,"overallRating":31,"rank":7,"speed":2,"firepower":6,"courage":5,"intelligence":9,"skill":10}

Delete /transformers
{"type":"D","name":"Predaking","strength":8,"endurance":6,"overallRating":31,"rank":7,"speed":2,"firepower":6,"courage":5,"intelligence":9,"skill":10}

Post /transformers/battle
[{"type":"D","name":"Predaking","strength":8,"endurance":6,"overallRating":31,"rank":7,"speed":2,"firepower":6,"courage":5,"intelligence":9,"skill":10},{"type":"A","name":"Bluestreak","strength":6,"endurance":9,"overallRating":37,"rank":5,"speed":7,"firepower":9,"courage":2,"intelligence":6,"skill":7}]

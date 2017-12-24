var vision = require('google-vision-api-client');
var requtil = vision.requtil;
 
//Prepare your service account from trust preview certificated project
var jsonfile = './bitCamp-703a07eaf6d0.json';
 
//Initialize the api
vision.init(jsonfile);
 
//Build the request payloads
var d = requtil.createRequests().addRequest(
    requtil.createRequest('./a.jpg')
        // .withFeature('FACE_DETECTION', 50)
        .withFeature('TEXT_DETECTION', 50)
        .build());
 
//Do query to the api server
vision.query(d, function(e, r, d){
	if(e) console.log('ERROR:', e);
	
	// var data = JSON.stringify(d)
	console.log(d.responses[0].fullTextAnnotation.text)
});
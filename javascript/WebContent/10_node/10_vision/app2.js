

const vision = require('@google-cloud/vision');

// Read the file into memory.
var fs = require('fs');
var imageFile = fs.readFileSync('./a.jpg');

// Covert the image data to a Buffer and base64 encode it.
var encoded = new Buffer(imageFile).toString('base64');

// Creates a client
const client = new vision.ImageAnnotatorClient();

/**
 * TODO(developer): Uncomment the following line before running the sample.
 */
const fileName = encoded;

// Performs text detection on the local file
client
  .textDetection(fileName)
  .then(results => {
    const detections = results[0].textAnnotations;
    console.log('Text:');
    // detections.forEach(text => console.log(text.description));
    // console.log(detections[0].description)
  })
  .catch(err => {
    console.error('ERROR:', err);
  });
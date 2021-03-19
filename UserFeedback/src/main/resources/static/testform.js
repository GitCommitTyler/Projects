
//get data from form
function SubmitTestForm() {
	var name= document.getElementById("name").value;
	var comments = document.getElementById("comment").value;
	var rating = document.getElementById("rating").value;
	var data = {user:name,comments:comments,rating:rating};
	postFormDataAsJson({url: "http://localhost:8090/feedback", formData: data })
}

/**
 * Helper function for POSTing data as JSON with fetch.
 *
 * @param {Object} options
 * @param {string} options.url - URL to POST data to
 * @param {FormData} options.formData - `FormData` instance
 * @return {Object} - Response body from URL that was POSTed to
 */
async function postFormDataAsJson({ url, formData }) {
	
	var myHeaders = new Headers();
myHeaders.append("Content-Type", "application/json; charset=UTF-8", );

//convert data from input fields to json
var raw = JSON.stringify(formData);
console.log(raw);

var requestOptions = {
  method: 'POST',
	mode: 'cors',
  headers: {
			"Content-Type": "application/json; charset=UTF-8",
			"Accept": "application/json"
		},
  body: raw,
  redirect: 'follow'
};

//not sure why it says failed to fetch a lot of the time. The request still goes through.
 await fetch("http://localhost:8090/feedback", requestOptions)
  .then(response => response.text())
  .then(result => alert(result))
  .catch(error => alert(error));
}
	
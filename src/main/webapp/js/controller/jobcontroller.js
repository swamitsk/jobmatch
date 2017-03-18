(function (){

	angular
		.module("jobFinder")
		.controller("jobController",JobController);
	
		
		function JobController($http){

			var vm = this;
			vm.empId = null;
			vm.response;
			vm.data = "";
			vm.getMatchingJobs = getMatchingJobs;
			
			function getMatchingJobs(empId)
			{
		        console.log(empId);
		        if(isNaN(empId))
		        	$http.get('webapi/jobsearch/jobs').then(saveResponse);
		        else
					$http.get('webapi/jobsearch/emp/' + empId).then(saveResponse);
		        
					

				//console.log(data);
			}
			
			function saveResponse(response)
			{
				vm.data = response.data;
			}
		}

		function alert()
		{
			console.log("ALERT");
		}


})();	

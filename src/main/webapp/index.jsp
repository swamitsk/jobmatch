<!DOCTYPE html>
<html lang="en" ng-app="jobFinder">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Job Search App</title>
    <!-- Bootstrap css and my own css -->
    <link rel="stylesheet" 
        href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
    <link rel="stylesheet" href="css/style.css">

</head>

<body>
   
    <div class="container">
        <div class="page-header">
            <h1>Job Search </h1>
        </div>

        <div ng-controller="jobController as pctrl">
        
	<form class="form-inline well well-sm clearfix">
		<span class="glyphicon glyphicon-search"></span>
		<input type = "text" name="name" ng-model = "inputValue" placeHolder = "Search using EmpID"></input>
		<button class="btn btn-warning" ng-click = "pctrl.getMatchingJobs(inputValue)">
			<strong> Search </strong>
		</button>
      </form>   
      <table ng-show="pctrl.data.length">
          <tr>
    <th>Index</th>
    <th>Job ID</th>
    <th>Company</th>
    <th>Start Date</th>
    <th>Job Title</th>
         <tr ng-repeat="job in pctrl.data">
                <td width="15%">{{$index+1}}</td>
                <td width="15%">{{ job.jobId }}</td>
                  <td width="20%">{{ job.company }}</td>
                  <td width="30%">{{ job.startDate }}</td>
                  <td width="30%">{{ job.jobTitle }}</td>
               
        </tr>
        </table>
        </div>

    </div>
     <!-- third party js -->
     <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.7/angular.js"></script> 
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- application scripts -->

    <script type="text/javascript" src="js/app.js"></script>
    <script type="text/javascript" src="js/controller/jobcontroller.js"></script>


</body>
</html>
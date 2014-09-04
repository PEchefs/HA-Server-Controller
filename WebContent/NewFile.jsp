<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%-- <%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%> --%>
    <%-- <%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%> --%>

    <script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" >
			
	    $(document).ready(function(){
	    	$('#autoConfiguration').hide();
	    	$('#result_failure').hide();
	    	$('#result_success').hide();
	    	
	    	$("#useHetNet").click(function() {
	    		if($("#useHetNet").is(':checked'))
		    		$('#autoConfiguration').show();
		    	else
		    		$('#autoConfiguration').hide();
	    	});
	    	
	    });
	    
		function sendAjax() {
			var enableCANR;
			if ($("#enableCANR").is(':checked')) {
				enableCANR = "true";
			}
			else {
				enableCANR = "false";
			}
			
			var cellRangeOption = $('input[name=radCalc]:checked').val();
			
			var useHetNet;
			if ($("#useHetNet").is(':checked')) {
				useHetNet = "true";
			}
			else {
				useHetNet = "false";
			}
			
			var searchDist= $('#searchDistance').val();
			var minAdj=$('#minAdjacencies').val();
			var maxAdj=$('#maxAdjacencies').val();
			
			var jsonData = {
				"Enable Central ANR" : enableCANR,
				"Cell Range Option" : cellRangeOption,
				"Use Het Net" : useHetNet,
				"searchDistance":searchDist,
				"minimumNoOfNeighbors":minAdj,
				"maximumNoOfNeighbours":maxAdj
			};
			
			
			$.ajax({
				url: "/AutoConfiguration/SaveSettingsServlet",
				type: 'POST',
				//contentType: 'application/json',
				dataType: 'json',
				data: jsonData,
				
				success: function(response) {
					alert("Success!");
					console.log("Enable CM Refresh: " + response['Enable Central ANR']);
					console.log("Cell Range Option: " + response['Cell Range Option']);
					console.log("Use Het Net: " + response['Use Het Net']);
					$("#hetNet").show();
					$('#autoConfiguration').show();
					$('#result_success').show();
					$('#result_failure').hide();
					
				},
				
				error: function(response) {
					alert("Error :: ");
					$('#autoConfiguration').show();
					$('#result_failure').show();
					$('#result_success').hide();
				}
			});  
			
		}

    </script>

</head>
<body>
	<div id="result_success">
		<h2>Settings saved successfully!</h2>
	</div>
	<div id="result_failure">
		<h2>Error while saving settings...</h2>
	</div>
	<br>
	<div id="hetNet"  >
		<input type="checkbox" id="useHetNet" name="useHetNet"> Neighbour Detection with Heterogeneous Networks </input>
	</div>
	<br>
	<div id="autoConfiguration" style="min-width: 350px; min-height: 500px;">
		<div id="settings" >
			<input type="checkbox" id="enableCANR" name="enableCANR"> Enable CM Refresh </input>
		</div>
        <br><hr><br>
        <div id="centralANROptions">
        	<span>
        		<h3>Cell Range Calculation</h3><br>
        		<input type="radio" name="radCalc" value="Path Loss Model" >Path Loss Model</input>
        		<br>
        		<input type="radio" name="radCalc" value="Expected Cell Range">Expected Cell Range</input>
        		<br>
        		<input type="radio" name="radCalc" value="Static Values">Static Values</input>
        		<br><br>
        	</span>
        	<br>
        	<table>
				<tr>
					<td>Search Distance</td>
					<td><input id="searchDistance" type="text" /></td>
				</tr>
				<tr>
					<td>Minimum adjacencies</td>
					<td><input id="minAdjacencies" type="text" /></td>
				</tr>
				<tr>
					<td>Maximum adjacencies</td>
					<td><input id="maxAdjacencies" type="text" /></td>
				</tr>
			</table>
        </div>
        
        
	</div>
	<div id="footer" align="center">
        	<input type="button" id="saveSettings" value="Save" onClick="sendAjax()" />
        </div>
</body>
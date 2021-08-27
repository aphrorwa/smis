$(function() {

	// Set the default dates
	var startDate = Date.create().addDays(-6), // 7 days ago
	endDate = Date.create(); // today

	var range = $('#range');

	// Show the dates in the range input
	range.val(startDate.format('{MM}/{dd}/{yyyy}') + ' - '
			+ endDate.format('{MM}/{dd}/{yyyy}'));

	// Load chart
	ajaxLoadChart(startDate, endDate);

	range.daterangepicker({
		startDate : startDate,
		endDate : endDate,
		ranges : {
			'Today' : [ 'today', 'today' ],
			'Yesterday' : [ 'yesterday', 'yesterday' ],
			'Last 7 Days' : [ Date.create().addDays(-6), 'today' ],
			'Last 30 Days' : [ Date.create().addDays(-29), 'today' ],
			'Last 365 Days' : [ Date.create().addDays(-364), 'today' ]
		}
	}, function(start, end) {
		ajaxLoadChart(start, end);
	});

	// Function for loading data via AJAX and showing it on the chart
	function ajaxLoadChart(startDate, endDate) {
		// If no data is passed (the chart was cleared)
		if (!startDate || !endDate) {
			return;
		}
		// Otherwise, issue an AJAX request
		$.post('http://localhost:8089/rgbecosys/highchart.apff', {
			start : startDate.format('{MM}/{dd}/{yyyy}'),
			end : endDate.format('{MM}/{dd}/{yyyy}')
		}, function(data) {
			if ((data.indexOf("No record found") > -1)
					|| (data.indexOf("Date must be selected.") > -1)) {
				$('#msg').html('<span style="color:red;">' + data + '</span>');
			} else {
				$('#msg').empty();
				$('#chart').highcharts({
					chart : {
						type : 'arearange',
						zoomType : 'x',
					},

					title : {
						text : 'Consumable assets variation by purchases and remaining stock'
					},

					xAxis : {
						type : 'datetime'
					},

					yAxis : {
						title : {
							text : 'Number of items in stock'
						}
					},

					tooltip : {
						crosshairs : true,
						shared : true,
						valueSuffix : ' Assets'
					},

					legend : {
						enabled : true
					},

					series : [ {
						name : 'Historical number of products in stock and in use',
						data : data
					} ]
				});
			}
		}, 'json');
	}
});

/**
 * 
 */

var TrainingApiUtils = {

	convertDataFn: function (data, microApps) { 
		var parsedData = {};
		microApps.forEach(microApp => {
			microApp === 'clientsidechartMicroApp' ? parsedData[microApp] = TrainingAPIUtils.convertToChartData(data) : parsedData[microApp] = data;
		})
		return parsedData;
	},

	convertToChartData: function (data) { 
		var chartData = [];
		var chartSeries = {};
		chartSeries['id'] = 'value';
		chartSeries['value'] = 'value';
		chartSeries['type'] = 'line';
		var seriesData = [];
		for (j = 0; j < data.length; j++) {
			var point = [];
			point[0] = data[j].customerId;
			point[1] = data[j].max;
			seriesData.push(point);
		}
		chartSeries['data'] = seriesData;
		chartData.push(chartSeries);
		return chartData;
	}

};
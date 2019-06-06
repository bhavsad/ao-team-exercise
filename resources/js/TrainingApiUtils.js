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
	},
	
	openWindowApp: function () {
		var appUtil = new window['AppUtil']();
		var appConfig = {
			id: 'com::apporchid::aoTeamExercise::CustomCSSMicroApp',
			solutionId: 'com::apporchid::aoTeamExercise::aoTeamExercise',
			maxWidth: 350,
			maxHeight: 292,
			minWidth: 150,
			minHeight: 150,
			relativePosition : {
				relativeCompId: 'openWindowAppId',
				y: 50,
				x: 50
			}
		}
		appUtil.openApplication(appConfig);
	}

};
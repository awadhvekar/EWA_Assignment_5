google.charts.load('current', {packages: ['corechart', 'bar']});
// google.charts.setOnLoadCallback(drawStacked);

//Bind click event to make an ajax call to post request of DataVisualization. This will return
// a json object with top 3 review for each city;
/*
$("#btnGetChartData").click(function () {
     $("#btnGetChartData").hide();
*/
    $.ajax({
        url: "DataVisualization",
        type: "POST",
        data: "{}",
        success: function (msg) {
            createDataTable(msg)            
        },
        error: function(){
            console.log("error occurred while making ajax call;")
        }
    });    
//});


//This method will parse json data and build datatable required by google api to plot the bar chart.
function createDataTable(jsonData) {
    //alert(jsonData);
    /*
    [{"productName":"samsung_phone","reviewRating":"2","retailerpin":"60616"},{"productName":"premium","reviewRating":"1","retailerpin":"60616"},{"productName":"samsung_smart_watch","reviewRating":"1","retailerpin":"60616"},{"productName":"samsung_headphone","reviewRating":"1","retailerpin":"12345"},{"productName":"samsung_sound_system","reviewRating":"1","retailerpin":"92688"},{"productName":"sony_tv","reviewRating":"1","retailerpin":"92688"},{"productName":"microsoft_tv","reviewRating":"1","retailerpin":"92456"}]
    */
    var parsedData = $.parseJSON(jsonData);
    //alert(parsedData);
    /*
    [object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object]
    */
    var data = new Array();
    var productNameArr = new Array();
    var zipCodeArr = new Array();


    //Create an array of product name and an array of zipcodes
    for(var i=0; i<parsedData.length; i++) {
        var productName = parsedData[i]["productName"];
        var zipCode = parsedData[i]["retailerpin"];
        if(!productNameArr.includes(productName)) {
            productNameArr.push(productName);
        }
        if(!zipCodeArr.includes(zipCode)) {
            zipCodeArr.push(zipCode);
        }
     }

    //alert("productNameArr: " + productNameArr + " \nzipCodeArr: " + zipCodeArr);
     /*
    productNameArr: samsung_phone,premium,samsung_smart_watch,samsung_headphone,samsung_sound_system,sony_tv,microsoft_tv 
    zipCodeArr: 60616,12345,92688,92456
    */

     //Create header array for google api
     var headingArray = new Array(productNameArr.length+1);
     headingArray[0] = "Zip Code";
     var j=0;

     for(var i=1; i<=productNameArr.length; i++) {
        headingArray[i] = productNameArr[j]; 
        j++;
     }

    //alert("headingArray: " + headingArray);
    //headingArray: Zip Code,samsung_phone,premium,samsung_smart_watch,samsung_headphone,samsung_sound_system,sony_tv,microsoft_tv

     data[0] = headingArray;
     var m =1;
    
     //Loop through jsondata and create an array of arrays to plot the chart;
    for(var i=0; i<zipCodeArr.length; i++) {
        var dataArr = new Array(headingArray.length);
        dataArr[0] = zipCodeArr[i];
        for(var j=0; j<productNameArr.length; j++) {
            for(k=0; k<parsedData.length; k++) {
                if(parsedData[k]["retailerpin"] === zipCodeArr[i] && parsedData[k]["productName"] === productNameArr[j]) {                    
                    dataArr[j+1] = parseInt(parsedData[k]["reviewRating"]);                   
                }                 
            }

        }
        
        //Set empty cell elements to zero;
        for(var n=1; n<headingArray.length; n++) {
            if(!(dataArr[n] > 0)) {
                dataArr[n] = 0;
            }
        }
        data[m] = (dataArr);
        m++;
        
     }
    //alert(data);
    //Zip Code,samsung_phone,premium,samsung_smart_watch,samsung_headphone,samsung_sound_system,sony_tv,microsoft_tv,60616,2,1,1,0,0,0,0,12345,0,0,0,1,0,0,0,92688,0,0,0,0,1,1,0,92456,0,0,0,0,0,0,1
    drawChart(data, productNameArr);
}

//Plot the chart using 2d array and product names as subtitles;
function drawChart(data, productNameArr) {
    //alert(typeof(data));
    var productNames = "";
    for(var i=0; i<productNameArr.length; i++) {
        productNames += productNameArr[i] + ",";
    }


    //Invoke google's built in method to get data table object required by google.
     var chartData = google.visualization.arrayToDataTable(data);
     /*
    [
        ['Product Name', 'Number of available products'],
        ['2014', 10],
        ['2015', 12],
        ['2016', 7],
        ['2017', 15]
    ]
     */

     var options = {
        'width':600,
        'height':650,
          chart: {
            title: 'Trending Products Chart',
            subtitle: productNames,
          },
          bars: 'horizontal' // Required for Material Bar Charts.
        };

    var chart = new google.visualization.BarChart(document.getElementById('chart_div')); //chartDivNumberOfAvailableProducts chart_div
    chart.draw(chartData, options);
}





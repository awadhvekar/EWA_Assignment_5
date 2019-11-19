google.charts.load('current', {packages: ['corechart', 'bar']});

$.ajax({
    url: "TotalProductSalesAPI",
    type: "POST",
    data: "{}",
    contentType: "application/json; charset=utf-8",
    dataType: "json",
    success: function (msg) {
        //alert(msg);
        formatJsonAccordingToGoogleChart(msg)            
    },
    error: function(){
        console.log("error occurred while making ajax call;")
    }
});

function formatJsonAccordingToGoogleChart(msg)
{
    //zipcodeArray => productNameArr
    //productNameArr => productTotalSalesArr

    //alert(msg);
    //[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object]
    var newMsg = JSON.stringify(msg);
    //alert(newMsg);
    //[{"productName":"Basic Plan","productPrice":"22.99","noOfProductsSold":"3","productTotalSales":"68.97"},{"productName":"LG FitnessWatch","productPrice":"399.99","noOfProductsSold":"2","productTotalSales":"799.98"},{"productName":"LG Headphone","productPrice":"20.99","noOfProductsSold":"2","productTotalSales":"41.98"},{"productName":"LG Laptop","productPrice":"689.99","noOfProductsSold":"2","productTotalSales":"1379.98"},{"productName":"LG Phone","productPrice":"389.99","noOfProductsSold":"2","productTotalSales":"779.98"},{"productName":"LG SmartWatch","productPrice":"289.99","noOfProductsSold":"2","productTotalSales":"579.98"},{"productName":"LG SoundSystem","productPrice":"72.99","noOfProductsSold":"2","productTotalSales":"145.98"},{"productName":"LG Television","productPrice":"87.99","noOfProductsSold":"2","productTotalSales":"175.98"},{"productName":"LG VoiceAssistant","productPrice":"199.99","noOfProductsSold":"2","productTotalSales":"399.98"},{"productName":"Microsoft FitnessWatch","productPrice":"40.99","noOfProductsSold":"2","productTotalSales":"81.98"},{"productName":"Microsoft Headphone","productPrice":"149.99","noOfProductsSold":"2","productTotalSales":"299.98"},{"productName":"Microsoft Laptop","productPrice":"849.99","noOfProductsSold":"2","productTotalSales":"1699.98"},{"productName":"Microsoft Phone","productPrice":"589.99","noOfProductsSold":"2","productTotalSales":"1179.98"},{"productName":"Microsoft SmartWatch","productPrice":"389.99","noOfProductsSold":"2","productTotalSales":"779.98"},{"productName":"Microsoft SoundSystem","productPrice":"50.99","noOfProductsSold":"2","productTotalSales":"101.98"},{"productName":"Microsoft Television","productPrice":"150.49","noOfProductsSold":"3","productTotalSales":"451.47"},{"productName":"Microsoft VoiceAssistant","productPrice":"150.99","noOfProductsSold":"2","productTotalSales":"301.98"},{"productName":"Onida FitnessWatch","productPrice":"289.99","noOfProductsSold":"2","productTotalSales":"579.98"},{"productName":"Onida Headphone","productPrice":"89.99","noOfProductsSold":"2","productTotalSales":"179.98"},{"productName":"Onida Laptop","productPrice":"489.99","noOfProductsSold":"2","productTotalSales":"979.98"},{"productName":"Onida Phone","productPrice":"300.99","noOfProductsSold":"2","productTotalSales":"601.98"},{"productName":"Onida SmartWatch","productPrice":"149.99","noOfProductsSold":"2","productTotalSales":"299.98"},{"productName":"Onida SoundSystem","productPrice":"59.99","noOfProductsSold":"2","productTotalSales":"119.98"},{"productName":"Onida Television","productPrice":"75.99","noOfProductsSold":"2","productTotalSales":"151.98"},{"productName":"Onida VoiceAssistant","productPrice":"149.99","noOfProductsSold":"2","productTotalSales":"299.98"},{"productName":"Premium Plan","productPrice":"32.99","noOfProductsSold":"2","productTotalSales":"65.98"},{"productName":"Samsung FitnessWatch","productPrice":"499.99","noOfProductsSold":"2","productTotalSales":"999.98"},{"productName":"Samsung Headphone ","productPrice":"89.99","noOfProductsSold":"2","productTotalSales":"179.98"},{"productName":"Samsung Laptop","productPrice":"520.99","noOfProductsSold":"2","productTotalSales":"1041.98"},{"productName":"Samsung Phone","productPrice":"489.99","noOfProductsSold":"2","productTotalSales":"979.98"},{"productName":"Samsung SmartWatch","productPrice":"280.99","noOfProductsSold":"2","productTotalSales":"561.98"},{"productName":"Samsung SoundSystem","productPrice":"200.99","noOfProductsSold":"2","productTotalSales":"401.98"},{"productName":"Samsung Television","productPrice":"99.99","noOfProductsSold":"2","productTotalSales":"199.98"},{"productName":"Samsung VoiceAssistant","productPrice":"50.99","noOfProductsSold":"2","productTotalSales":"101.98"},{"productName":"Sony FitnessWatch","productPrice":"289.99","noOfProductsSold":"2","productTotalSales":"579.98"},{"productName":"Sony Headphone","productPrice":"189.99","noOfProductsSold":"2","productTotalSales":"379.98"},{"productName":"Sony Laptop","productPrice":"549.99","noOfProductsSold":"2","productTotalSales":"1099.98"},{"productName":"Sony Phone","productPrice":"189.99","noOfProductsSold":"2","productTotalSales":"379.98"},{"productName":"Sony SmartWatch","productPrice":"389.99","noOfProductsSold":"2","productTotalSales":"779.98"},{"productName":"Sony SoundSystem","productPrice":"112.99","noOfProductsSold":"2","productTotalSales":"225.98"},{"productName":"Sony Television","productPrice":"119.99","noOfProductsSold":"2","productTotalSales":"239.98"},{"productName":"Sony VoiceAssistant","productPrice":"149.99","noOfProductsSold":"2","productTotalSales":"299.98"},{"productName":"Ultimate Plan","productPrice":"42.99","noOfProductsSold":"2","productTotalSales":"85.98"}]
    var parsedData = $.parseJSON(newMsg);
    //alert(parsedData);
    //[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object]
    var data = new Array();
    var productNameArr = new Array();
    var productTotalSalesArr = new Array();

    //Create an array of product name and an array of zipcodes
    for(var i=0; i<parsedData.length; i++)
    {
        var productName = parsedData[i]["productName"];
        var productTotalSales = parsedData[i]["productTotalSales"];
        if(!productNameArr.includes(productName))
        {
            productNameArr.push(productName);
        }
        
        if(!productTotalSalesArr.includes(productTotalSales))
        {
            productTotalSalesArr.push(productTotalSales);
        }
    }
    //alert("productNameArr: " + productNameArr + " \nproductTotalSalesArr: " + productTotalSalesArr);
    
    //productNameArr: Basic Plan,LG FitnessWatch,LG Headphone,LG Laptop,LG Phone,LG SmartWatch,LG SoundSystem,LG Television,LG VoiceAssistant,Microsoft FitnessWatch,Microsoft Headphone,Microsoft Laptop,Microsoft Phone,Microsoft SmartWatch,Microsoft SoundSystem,Microsoft Television,Microsoft VoiceAssistant,Onida FitnessWatch,Onida Headphone,Onida Laptop,Onida Phone,Onida SmartWatch,Onida SoundSystem,Onida Television,Onida VoiceAssistant,Premium Plan,Samsung FitnessWatch,Samsung Headphone ,Samsung Laptop,Samsung Phone,Samsung SmartWatch,Samsung SoundSystem,Samsung Television,Samsung VoiceAssistant,Sony FitnessWatch,Sony Headphone,Sony Laptop,Sony Phone,Sony SmartWatch,Sony SoundSystem,Sony Television,Sony VoiceAssistant,Ultimate Plan 
    //productTotalSalesArr: 68.97,799.98,41.98,1379.98,779.98,579.98,145.98,175.98,399.98,81.98,299.98,1699.98,1179.98,101.98,451.47,301.98,179.98,979.98,601.98,119.98,151.98,65.98,999.98,1041.98,561.98,401.98,199.98,379.98,1099.98,225.98,239.98,85.98
    

    //Create header array for google api
    var headingArray = new Array(productTotalSalesArr.length+1);
    headingArray[0] = "Product Name";
    var j=0;

    for(var i=1; i<=productTotalSalesArr.length; i++)
    {
        headingArray[i] = productTotalSalesArr[j]; 
        j++;
    }
    //alert("headingArray: " + headingArray);
    //headingArray: Product Total Sales,Basic Plan,LG FitnessWatch,LG Headphone,LG Laptop,LG Phone,LG SmartWatch,LG SoundSystem,LG Television,LG VoiceAssistant,Microsoft FitnessWatch,Microsoft Headphone,Microsoft Laptop,Microsoft Phone,Microsoft SmartWatch,Microsoft SoundSystem,Microsoft Television,Microsoft VoiceAssistant,Onida FitnessWatch,Onida Headphone,Onida Laptop,Onida Phone,Onida SmartWatch,Onida SoundSystem,Onida Television,Onida VoiceAssistant,Premium Plan,Samsung FitnessWatch,Samsung Headphone ,Samsung Laptop,Samsung Phone,Samsung SmartWatch,Samsung SoundSystem,Samsung Television,Samsung VoiceAssistant,Sony FitnessWatch,Sony Headphone,Sony Laptop,Sony Phone,Sony SmartWatch,Sony SoundSystem,Sony Television,Sony VoiceAssistant,Ultimate Plan
    data[0] = headingArray;
    var m =1;

    //Loop through jsondata and create an array of arrays to plot the chart;
    for(var i=0; i<productNameArr.length; i++)
    {
        var dataArr = new Array(headingArray.length);
        dataArr[0] = productNameArr[i];
        for(var j=0; j<productTotalSalesArr.length; j++)
        {
            for(k=0; k<parsedData.length; k++)
            {
                if(parsedData[k]["productName"] === productNameArr[i] && parsedData[k]["productTotalSales"] === productTotalSalesArr[j])
                {
                    dataArr[j+1] = parseInt(parsedData[k]["noOfProductsSold"]);                   
                }                 
            }
        }

        //Set empty cell elements to zero;
        for(var n=1; n<headingArray.length; n++)
        {
            if(!(dataArr[n] > 0))
            {
                dataArr[n] = 0;
            }
        }
        data[m] = (dataArr);
        m++;

    }
    //alert(data);
    //Product Total Sales,Basic Plan,LG FitnessWatch,LG Headphone,LG Laptop,LG Phone,LG SmartWatch,LG SoundSystem,LG Television,LG VoiceAssistant,Microsoft FitnessWatch,Microsoft Headphone,Microsoft Laptop,Microsoft Phone,Microsoft SmartWatch,Microsoft SoundSystem,Microsoft Television,Microsoft VoiceAssistant,Onida FitnessWatch,Onida Headphone,Onida Laptop,Onida Phone,Onida SmartWatch,Onida SoundSystem,Onida Television,Onida VoiceAssistant,Premium Plan,Samsung FitnessWatch,Samsung Headphone ,Samsung Laptop,Samsung Phone,Samsung SmartWatch,Samsung SoundSystem,Samsung Television,Samsung VoiceAssistant,Sony FitnessWatch,Sony Headphone,Sony Laptop,Sony Phone,Sony SmartWatch,Sony SoundSystem,Sony Television,Sony VoiceAssistant,Ultimate Plan,68.97,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,799.98,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,41.98,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1379.98,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,779.98,0,0,0,0,2,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,579.98,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,145.98,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,175.98,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,399.98,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,81.98,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,299.98,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,2,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,1699.98,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1179.98,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,101.98,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,451.47,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,301.98,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,179.98,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,979.98,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,601.98,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,119.98,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,151.98,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,65.98,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,999.98,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1041.98,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,561.98,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,401.98,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,199.98,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,379.98,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,2,0,0,0,0,0,1099.98,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,225.98,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,239.98,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,85.98,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2
    drawChart(data, productNameArr);
}

//Plot the chart using 2d array and product names as subtitles;
function drawChart(data, productNameArr)
{
    //alert(typeof(data));
    var productNames = "";
    for(var i=0; i<productNameArr.length; i++)
    {
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
        //'width':600,
        'height':650,
        chart: {
            title: 'Total Product Sales',
            //subtitle: productNames,
        },
        bars: 'horizontal' // Required for Material Bar Charts.
    };

    var chart = new google.visualization.BarChart(document.getElementById('chartDivTotalProductSales')); //chartDivNumberOfAvailableProducts chart_div
    chart.draw(chartData, options);
}
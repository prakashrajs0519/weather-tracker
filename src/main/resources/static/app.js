angular.module('canvasApp', [])
  .controller('GraphController', function($scope) {
    // Sample JSON data (replace with your actual JSON data)
    var jsonData = [
      { temperature: 25, pressure: 1013, date: "2024-03-11T08:00:00Z" },
      { temperature: 26, pressure: 1012, date: "2024-03-11T09:00:00Z" },
      { temperature: 27, pressure: 1011, date: "2024-03-11T10:00:00Z" },
      // Add more data as needed
    ];

    $scope.drawGraph = function() {
      var canvas = document.getElementById('myCanvas');
      var ctx = canvas.getContext('2d');
      
      // Clear canvas
      ctx.clearRect(0, 0, canvas.width, canvas.height);
      
      // Draw temperature graph
      ctx.beginPath();
      ctx.strokeStyle = 'red';
      ctx.lineWidth = 2;
      jsonData.forEach(function(data, index) {
        var x = index * 100 + 50; // Adjust for spacing and padding
        var y = canvas.height - data.temperature * 2; // Adjust scale as needed
        if (index === 0) {
          ctx.moveTo(x, y);
        } else {
          ctx.lineTo(x, y);
        }
      });
      ctx.stroke();
      
      // Draw pressure graph
      ctx.beginPath();
      ctx.strokeStyle = 'blue';
      ctx.lineWidth = 2;
      jsonData.forEach(function(data, index) {
        var x = index * 100 + 50; // Adjust for spacing and padding
        var y = canvas.height - data.pressure / 5; // Adjust scale as needed
        if (index === 0) {
          ctx.moveTo(x, y);
        } else {
          ctx.lineTo(x, y);
        }
      });
      ctx.stroke();
      
      // Add axis labels
      ctx.fillStyle = 'black';
      ctx.font = '12px Arial';
      
      // X-axis labels (dates)
      jsonData.forEach(function(data, index) {
        var x = index * 100 + 50; // Adjust for spacing and padding
        ctx.fillText(data.date, x, canvas.height - 10);
      });
      
      // Y-axis labels (temperature)
      ctx.fillText('Temperature (°C)', 10, 20);
      
      // Y-axis labels (pressure)
      ctx.fillText('Pressure (hPa)', canvas.width - 90, 20);
    };
    
    // Call drawGraph function when controller initializes
    $scope.drawGraph();
  });

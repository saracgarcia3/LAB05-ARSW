var apimock = (function () {
  console.log("[apimock] cargado");

  var mockdata = {
    "sara": [
      {
        name: "plano1",
        points: [ {x:40, y:40}, {x:200, y:40}, {x:200, y:150}, {x:40, y:150}, {x:40, y:40} ]
      },
      {
        name: "plano2",
        points: [ {x:80, y:180}, {x:160, y:60}, {x:240, y:180}, {x:80, y:180} ]
      }
    ],
    "jhon": [
      {
        name: "planoA",
        points: [ {x:10, y:10}, {x:100, y:10}, {x:100, y:80}, {x:10, y:80}, {x:10, y:10} ]
      }
    ]
  };

  function getBlueprintsByAuthor(author, callback) {
    console.log("[apimock] getBlueprintsByAuthor:", author);
    setTimeout(function () {
      const result = mockdata[author] || [];
      console.log("[apimock] → devuelve", result);
      callback(result);
    }, 100);
  }

  function getBlueprintsByNameAndAuthor(author, name) {
    console.log("[apimock] getBlueprintsByNameAndAuthor:", author, name);
    const list = mockdata[author] || [];
    return list.find(bp => bp.name === name) || {name, points: []};
  }

  return {
    getBlueprintsByAuthor: getBlueprintsByAuthor,
    getBlueprintsByNameAndAuthor: getBlueprintsByNameAndAuthor
  };
})();

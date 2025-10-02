var app = (function () {
  console.log("[app] cargado");
  let author = null;
  let rows = []; 

  function toNamePoints(list) {
    return list.map(bp => ({ name: bp.name, points: (bp.points || []).length }));
  }

  function setAuthor(a) {
    author = a;
    $("#author-name").text(author || "—");
  }

  function renderTable() {
    const $tbody = $("#bp-table tbody");
    $tbody.empty();

    if (!rows.length) {
      $tbody.append(
        $("<tr>").append(
          $('<td colspan="3" class="text-muted">').text("Sin datos para este autor.")
        )
      );
      $("#total-points").text(0);
      return;
    }

    let total = 0;
    rows.forEach(r => {
      const $tr = $("<tr>");
      $tr.append($("<td>").text(r.name));
      $tr.append($("<td>").text(r.points));

      const $btn = $("<button>")
        .addClass("btn btn-sm btn-success")
        .text("Open")
        .on("click", () => drawBlueprint(r.name));

      $tr.append($("<td>").append($btn));
      $tbody.append($tr);

      total += r.points;
    });
    $("#total-points").text(total);
  }

  function drawBlueprint(bpName) {
    const bp = apimock.getBlueprintsByNameAndAuthor(author, bpName);
    $("#current-bp").text(bpName);

    const canvas = document.getElementById("bp-canvas");
    const ctx = canvas.getContext("2d");
    ctx.clearRect(0, 0, canvas.width, canvas.height);

    const pts = bp.points || [];
    if (!pts.length) return;

    ctx.beginPath();
    ctx.moveTo(pts[0].x, pts[0].y);
    for (let i = 1; i < pts.length; i++) {
      ctx.lineTo(pts[i].x, pts[i].y);
    }
    ctx.stroke();
  }

  function updateBlueprints() {
    const a = $("#author-input").val().trim();
    console.log("[app] updateBlueprints autor:", a);
    setAuthor(a);
    apimock.getBlueprintsByAuthor(a, function (list) {
      console.log("[app] planos recibidos:", list);
      rows = toNamePoints(list);
      renderTable();
    });
  }

  return { updateBlueprints };
})();

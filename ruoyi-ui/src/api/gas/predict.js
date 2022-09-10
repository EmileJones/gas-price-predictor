import service from "@/utils/request"

export function calculator(query) {
  return service({
    url: "/gas/price/calculation",
    method: "post",
    data: query
  });
}

export function getOilTypes() {
  return service({
    url: "/gas/price/oilType",
    method: "get"
  });
}

export function getOilManageData(oilType) {
  return service({
    url: `/gas/price/referenceData/${oilType}`,
    method: "post",
  })
}

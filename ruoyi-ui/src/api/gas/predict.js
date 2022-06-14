import service from "@/utils/request"

export function calculator(query) {
  return service({
    url: "/gas/price/calculation",
    method: "post",
    data: query
  });
}

export function getOilTypes(){
  return service({
    url: "/gas/price/oilType",
    method: "get"
  });
}

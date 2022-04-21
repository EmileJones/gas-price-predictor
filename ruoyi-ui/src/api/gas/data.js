import service from "@/utils/request"

export function getSaleData(query) {
  return service({
    url: "/gas/price/saleData",
    method: "get",
    params: query
  });
}

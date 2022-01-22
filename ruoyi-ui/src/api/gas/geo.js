import request from '@/utils/request'

// 查询加油站地理信息列表
export function listGeo(query) {
  return request({
    url: '/gas/geo/list',
    method: 'get',
    params: query
  })
}

// 查询加油站候选项
export function listGasStationCandidate(queryParam) {
  return request({
    url: '/gas/geo/station',
    method: 'get',
    params: queryParam
  })
}

import request from '@/utils/request'

// 查询加油站关键参数详细
export function getArgument(name) {
  return request({
    url: '/gas/argument/' + name,
    method: 'get'
  })
}

// 修改加油站关键参数
export function updateArgument(data) {
  return request({
    url: '/gas/argument',
    method: 'put',
    data: data
  })
}

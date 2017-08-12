import React, { Component } from 'react'
import Box from 'grommet/components/Box'
import Heading from 'grommet/components/Heading'
import Layer from 'grommet/components/Layer'
import CheckmarkIcon from 'grommet/components/icons/base/Checkmark'

export default class JenioSuccessModal extends Component {
    render() {
        return (
            <Layer onClose={() => {
                this.props.toggleJenioSuccessModal()
            }}>
                <Box
                    align='center'
                    pad='small'>
                    <Box
                        align='center'
                        pad='medium'>
                        <Heading
                            tag='h3'
                            align='center'
                            margin='none'>
                            Payment Successful
                        </Heading>
                    </Box>
                    <Box
                        align='center'
                        pad='medium'>
                        <CheckmarkIcon size='large' />
                    </Box>
                    <Box
                        align='center'
                        pad='medium'>
                        <Heading
                            tag='h4'
                            align='center'
                            margin='none'>
                        </Heading>
                        <Heading
                            tag='h4'
                            align='center'
                            margin='none'>
                            {`Sent amount: $${this.props.amount}`}
                        </Heading>
                    </Box>
                </Box>
            </Layer>
        )
    }
}